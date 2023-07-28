package com.example.elasticsearch.service;

import com.example.elasticsearch.entity.JobDetailBean;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author 江南
 * @date 2023/3/12
 */
public interface JobFullTextService {

    /**
     * 添加数据
     * @param request
     */
    void  add(JobDetailBean request) throws IOException;

    /**
     * 关闭es连接
     * @throws IOException
     */
    void close() throws IOException;

    /**
     * 通过Id获取数据
     * @param id
     * @return
     */
    JobDetailBean findById(Long id) throws IOException;


    /**
     * 更新es数据
     * @param request
     * @throws IOException
     */
    void updateDataById(JobDetailBean request) throws IOException;


    /**
     * 删除ES数据
     * @param id
     * @throws IOException
     */
    void deleteDataById(Long id) throws IOException;


    /**
     * 关键字检索
     * @param keywords
     * @return
     * @throws IOException
     */
    List<JobDetailBean>  searchByKeywords(String keywords) throws IOException;

    /**
     * 分页检索
     * @param keywords
     * @param pageIndex
     * @param pageSize
     * @return
     * @throws IOException
     */
    Map<String,Object> searchByKeywordsPage(String keywords,Integer pageIndex,Integer pageSize) throws IOException;

    /**
     * 深分页检索
     * @param keywords
     * @param scroll
     * @param pageSize
     * @return
     * @throws IOException
     */
    Map<String,Object> searchByKeywordsScroll(String keywords,String scroll,Integer pageSize) throws IOException;

    List<JobDetailBean>termQueryKeyword(String keywords,String fileName) throws IOException;
}
