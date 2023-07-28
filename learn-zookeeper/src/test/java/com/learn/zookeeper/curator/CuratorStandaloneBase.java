package com.learn.zookeeper.curator;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.data.Stat;
import org.junit.After;
import org.junit.Before;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

/**
 * @author ChenYP
 * @date 2023/7/26 10:12
 * @Describe
 */
@SpringBootTest
@Slf4j
public class CuratorStandaloneBase {

    private static final String CONNECT_STR = "192.168.17.128:2181";

    private static final int SESSION_TIMEOUT = 30 * 1000;

    private static final int connectionTImeOutMs = 5000;

    private static CuratorFramework curatorFramework;


    @Before
    public void  init(){
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(5000,30);
        curatorFramework = CuratorFrameworkFactory.builder().connectString(getConnectStr())
                .retryPolicy(retryPolicy)
                .sessionTimeoutMs(getSessionTimeOut())
                .connectionTimeoutMs(connectionTImeOutMs)
                .canBeReadOnly(true)
                .build();
        curatorFramework.getConnectionStateListenable().addListener((client,newState)->{
            if (newState == ConnectionState.CONNECTED){
                log.info("连接成功");
            }
        });
        log.info("连接中");
        curatorFramework.start();
    }


    public void createIfNeed(String path) throws Exception {
        Stat stat = curatorFramework.checkExists().forPath(path);
        if (stat == null){
            String s = curatorFramework.create().forPath(path);
            log.info("Path:{} create!",s);
        }
    }

    public static CuratorFramework getCuratorFramework(){
        return curatorFramework;
    }



    @After
    public void test(){
        try {
            TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getConnectStr(){
        return CONNECT_STR;
    }


    public int getSessionTimeOut(){
        return SESSION_TIMEOUT;
    }

}
