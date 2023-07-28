package com.learn.zookeeper.curator.controller;

import com.learn.zookeeper.curator.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ChenYP
 * @date 2023/7/26 17:16
 * @Describe
 */
@Slf4j
@RestController
public class TestController {

    @Autowired
    private OrderService orderService;


    @Value("${server.port}")
    private String port;

    @Autowired
    CuratorFramework curatorFramework;

    @RequestMapping(value = "/stock/deduct")
    public String queryAll(@RequestParam("id")Integer id) throws Exception {
        InterProcessMutex interProcessMutex = new InterProcessMutex(curatorFramework, "/product_" + id);
        try {
            interProcessMutex.acquire();
            orderService.reduceStock(id);
            log.info("访问成功:{}",port);


        } catch (Exception e) {
            if (e instanceof RuntimeException) {
                e.printStackTrace();
                throw e;
            }
        } finally {
            interProcessMutex.release();
        }
        return "ok";
    }



}
