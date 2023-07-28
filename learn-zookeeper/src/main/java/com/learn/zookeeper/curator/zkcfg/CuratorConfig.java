package com.learn.zookeeper.curator.zkcfg;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ChenYP
 * @date 2023/7/26 16:19
 * @Describe
 */
@Configuration
public class CuratorConfig {

    @Bean(initMethod = "start")
    public CuratorFramework curatorFramework(){
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient("192.168.17.128:2181", retryPolicy);
        return client;
    }
}
