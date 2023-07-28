package com.example.elasticsearch.service;

import com.example.elasticsearch.entity.JobDetailBean;
import com.example.elasticsearch.service.impl.JobFullTextServiceImpl;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author 江南
 * @date 2023/3/12
 */
public class JobFullTextServiceTest {

    private JobFullTextService jobFullTextService;

    @BeforeTest
    public void beforeTest(){
        jobFullTextService = new JobFullTextServiceImpl();
    }

    @Test
    public void addTest() throws IOException {
        JobDetailBean jobDetailBean = new JobDetailBean();
        jobDetailBean.setId(6L);
        jobDetailBean.setArea("北京");
        jobDetailBean.setCmp("清华大学");
        jobDetailBean.setEdu("本科及以上");
        jobDetailBean.setExp("五年工作经验");
        jobDetailBean.setTitle("java 高级架构师");
        jobDetailBean.setJobType("全职");
        jobDetailBean.setJd("python");
        jobDetailBean.setSalary("60K/月");
        jobDetailBean.setAge(30);
        jobFullTextService.add(jobDetailBean);
    }

    @Test
    public void getDataById() throws IOException {
        System.out.println(jobFullTextService.findById(4L));

    }

    @Test
    public void updateDataById() throws IOException {
        JobDetailBean jobDetailBean = jobFullTextService.findById(4L);

        jobDetailBean.setTitle("java 高级架构师");
        jobFullTextService.updateDataById(jobDetailBean);
    }

    @Test
    public void deleteDataById() throws IOException {
        jobFullTextService.deleteDataById(5L);

    }

    @Test
    public void searchByKeywords() throws IOException {
        List<JobDetailBean> jobDetailBeans = jobFullTextService.searchByKeywords("python");
        for (JobDetailBean jobDetailBean : jobDetailBeans) {
            System.out.println(jobDetailBean);
        }

    }

    @Test
    public void searchByKeywordsPage() throws IOException {
        Map<String,Object> map =  jobFullTextService.searchByKeywordsPage("java 高级架构师",1,1);
        System.out.println(map.get("total"));
        List<JobDetailBean> content = (List<JobDetailBean>)map.get("content");
        for (JobDetailBean jobDetailBean : content) {
            System.out.println(jobDetailBean);
        }

    }

    @Test
    public void searchByKeywordsScroll() throws IOException {
        Map<String,Object> map =  jobFullTextService.searchByKeywordsScroll("java 高级架构师",null,1);
        System.out.println(map.get("totalNum"));
        System.out.println(map.get("scroll_id"));
        List<JobDetailBean> content = (List<JobDetailBean>)map.get("content");
        for (JobDetailBean jobDetailBean : content) {
            System.out.println(jobDetailBean);
        }

    }

    @Test
    public void searchByKeywordsHasScroll() throws IOException {
        Map<String,Object> map =  jobFullTextService.searchByKeywordsScroll("java 高级架构师","DXF1ZXJ5QW5kRmV0Y2gBAAAAAAAANLYWUmF5RXJvY3dRVlNfclJWakQ1TDNTZw==",1);
        System.out.println(map.get("totalNum"));
        System.out.println(map.get("scroll_id"));
        List<JobDetailBean> content = (List<JobDetailBean>)map.get("content");
        for (JobDetailBean jobDetailBean : content) {
            System.out.println(jobDetailBean);
        }

    }


    @Test
    public void termQueryKeyword() throws IOException {
        List<JobDetailBean> jobDetailBeans = jobFullTextService.termQueryKeyword("python","jd");
        for (JobDetailBean jobDetailBean : jobDetailBeans) {
            System.out.println(jobDetailBean);
        }

    }





    @AfterTest
    public void afterClose() throws IOException {
        jobFullTextService.close();
    }
}
