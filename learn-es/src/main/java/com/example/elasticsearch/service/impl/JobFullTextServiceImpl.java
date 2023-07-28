package com.example.elasticsearch.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.elasticsearch.entity.JobDetailBean;
import com.example.elasticsearch.service.JobFullTextService;
import org.apache.http.HttpHost;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchScrollRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 江南
 * @date 2023/3/12
 */
public class JobFullTextServiceImpl implements JobFullTextService {

    private RestHighLevelClient redRestHighLevelClient;

    /**
     * 索引库名称
     */
    private static final String JOB_INDEX = "job_index";

    /**
     * 连接es
     */
    public JobFullTextServiceImpl() {
        RestClientBuilder restBuilder = RestClient.builder(new HttpHost("192.168.81.131", 9200, "http"));
        redRestHighLevelClient = new RestHighLevelClient(restBuilder);
    }

    @Override
    public void add(JobDetailBean request) throws IOException {
        //1、构建IndecentRequest对象，用来描述ES发起请求的数据
        IndexRequest indexRequest = new IndexRequest(JOB_INDEX);

        //2、设置文档ID
        indexRequest.id(request.getId() + "");

        //3、使用FastJSON 将实体类对象转换为JSON
        String jsonData = JSONObject.toJSONString(request);

        //4、使用IndexRequest.source方法设置文档数据，并设置请求的数据为JSON格式
        indexRequest.source(jsonData, XContentType.JSON);

        //5、使用ES High client 调用index方法发起请求，将一个文档添加到索引中
        redRestHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);


    }

    @Override
    public JobDetailBean findById(Long id) throws IOException {
        //1、构建Get Request请求
        GetRequest getRequest = new GetRequest(JOB_INDEX, id + "");

        //2、使用RestHighLevelClient.get发送GetRequest请求，并获取ES服务器响应
        GetResponse getResponse = redRestHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
        //3、将ES相应的数据转换为JSON字符串
        String jsonData = getResponse.getSourceAsString();

        //4、并使用FastJSON字符串转换为Job DetailBean
        JobDetailBean jobDetailBean = JSONObject.parseObject(jsonData, JobDetailBean.class);

        //5、记得：单独设置ID
        jobDetailBean.setId(id);

        return jobDetailBean;
    }

    @Override
    public void updateDataById(JobDetailBean request) throws IOException {
        //1、判断对应ID的文档是否存在
        GetRequest getRequest = new GetRequest(JOB_INDEX, request.getId() + "");

        //2、执行redRestHighLevelClient的exists方法，发起请求，判断是否存在
        boolean exists = redRestHighLevelClient.exists(getRequest, RequestOptions.DEFAULT);
        if (exists) {
            //3、构建UpdateRequest的文档，并配置为JSON格式
            UpdateRequest updateRequest = new UpdateRequest(JOB_INDEX, request.getId() + "");
            updateRequest.doc(JSONObject.toJSONString(request), XContentType.JSON);

            //4、执行client发起update请求
            redRestHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
        }

    }

    @Override
    public void deleteDataById(Long id) throws IOException {
        DeleteRequest deleteRequest = new DeleteRequest(JOB_INDEX, id + "");
        redRestHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
    }

    @Override
    public List<JobDetailBean> searchByKeywords(String keywords) throws IOException {
        //创建专门用来全文检索、关键字检索的API
        SearchRequest searchRequest = new SearchRequest(JOB_INDEX);

        //创建一个SearchSourceBuilder撞门用于构建查询条件
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        //使用QueryBuilder.multiMatchQuery 构建一个查询条件（搜索title，jd），并配置到SearchSourceBuilder
        MultiMatchQueryBuilder multiMatchQuery = QueryBuilders.multiMatchQuery(keywords, "title", "jd");
        //将查询条件设置到查询请求构建器中
        searchSourceBuilder.query(multiMatchQuery);
        //调用SearchRequest.source将查询条件设置到检索请求中
        searchRequest.source(searchSourceBuilder);
        //执行 RestHighLevelClient.search发起请求
        SearchResponse searchResponse = redRestHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        List<JobDetailBean> list = new ArrayList<>();
        //遍历结果
        SearchHit[] hits = searchResponse.getHits().getHits();
        for (SearchHit hit : hits) {
            String sourceAsString = hit.getSourceAsString();
            JobDetailBean jobDetailBean = JSONObject.parseObject(sourceAsString, JobDetailBean.class);
            jobDetailBean.setId(Long.parseLong(hit.getId()));
            list.add(jobDetailBean);
        }

        return list;
    }

    @Override
    public Map<String, Object> searchByKeywordsPage(String keywords, Integer pageIndex, Integer pageSize) throws IOException {

        SearchRequest searchRequest = new SearchRequest(JOB_INDEX);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        MultiMatchQueryBuilder multiMatchQueryBuilder = new MultiMatchQueryBuilder(keywords, "title", "jd");
        searchSourceBuilder.query(multiMatchQueryBuilder);
        searchRequest.source(searchSourceBuilder);
        searchSourceBuilder.from((pageIndex - 1) * pageSize);
        searchSourceBuilder.size(pageSize);
        SearchResponse search = redRestHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        List<JobDetailBean> resp = new ArrayList<>();

        for (SearchHit hit : search.getHits().getHits()) {
            JobDetailBean jobDetailBean = JSONObject.parseObject(hit.getSourceAsString(), JobDetailBean.class);
            jobDetailBean.setId(Long.parseLong(hit.getId()));
            resp.add(jobDetailBean);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("total", search.getHits().getTotalHits().value);
        map.put("content", resp);
        return map;
    }


    @Override
    public Map<String, Object> searchByKeywordsScroll(String keywords, String scroll, Integer pageSize) throws IOException {

        SearchResponse searchResponse = null;

        if (scroll == null) {
            SearchRequest searchRequest = new SearchRequest(JOB_INDEX);
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

            MultiMatchQueryBuilder query = new MultiMatchQueryBuilder(keywords, "title", "jd");
            searchSourceBuilder.query(query);

            //设置高亮
            HighlightBuilder highlightBuilder = new HighlightBuilder();
            highlightBuilder.field("title");
            highlightBuilder.field("jd");
            highlightBuilder.preTags("<font color='red'>");
            highlightBuilder.postTags("</font>");
            //给请求中设置高亮
            searchSourceBuilder.highlighter(highlightBuilder);
            //每页显示多少条
            searchSourceBuilder.size(pageSize);
            searchRequest.source(searchSourceBuilder);
            // 设置scroll查询
            //--------------------------
            searchRequest.scroll(TimeValue.timeValueMinutes(1));
            searchResponse = redRestHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        } else {
            SearchScrollRequest searchRequestScroll = new SearchScrollRequest(scroll);
            searchRequestScroll.scroll(TimeValue.timeValueMillis(1));
            searchResponse = redRestHighLevelClient.scroll(searchRequestScroll, RequestOptions.DEFAULT);
        }

        List<JobDetailBean> list = new ArrayList<>();
        for (SearchHit hit : searchResponse.getHits().getHits()) {
            JobDetailBean jobDetailBean = JSONObject.parseObject(hit.getSourceAsString(), JobDetailBean.class);
            jobDetailBean.setId(Long.parseLong(hit.getId()));
            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            HighlightField title = highlightFields.get("title");
            HighlightField jd = highlightFields.get("jd");
            if (title != null){
                Text[] fragments = title.getFragments();
                StringBuilder str= new StringBuilder();
                for (Text fragment : fragments) {
                    str.append(fragment);
                }
                jobDetailBean.setTitle(str.toString());
            }

            if (jd != null){
                Text[] fragments = jd.getFragments();
                StringBuilder str= new StringBuilder();
                for (Text fragment : fragments) {
                    str.append(fragment);
                }
                jobDetailBean.setJd(str.toString());
            }
            list.add(jobDetailBean);
        }

        long totalNum = searchResponse.getHits().getTotalHits().value;
        Map<String,Object> hashMap = new HashMap<>(16);
        hashMap.put("scroll_id", searchResponse.getScrollId());
        hashMap.put("totalNUm", totalNum);
        hashMap.put("content", list);
        return hashMap;
    }

    @Override
    public List<JobDetailBean> termQueryKeyword(String keywords, String fileName) throws IOException {
        SearchRequest searchRequest = new SearchRequest(JOB_INDEX);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        TermsQueryBuilder termsQueryBuilder = QueryBuilders.termsQuery(fileName, keywords);
        searchSourceBuilder.query(termsQueryBuilder);
        searchRequest.source(searchSourceBuilder);
        SearchResponse search = redRestHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        List<JobDetailBean> list = new ArrayList<>();
        for (SearchHit hit : search.getHits().getHits()) {
            JobDetailBean jobDetailBean = JSONObject.parseObject(hit.getSourceAsString(), JobDetailBean.class);
            jobDetailBean.setId(Long.parseLong(hit.getId()));
            list.add(jobDetailBean);
        }
        return list;
    }

    @Override
    public void close() throws IOException {
        redRestHighLevelClient.close();
    }
}
