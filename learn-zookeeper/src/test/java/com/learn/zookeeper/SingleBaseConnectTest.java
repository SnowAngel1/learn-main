package com.learn.zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.junit.After;
import org.junit.Before;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author ChenYP
 * @date 2023/7/25 16:49
 * @Describe
 */
@Slf4j
@SpringBootTest
public class SingleBaseConnectTest {



    private static final String CONNECT_STR = "192.168.17.128:2181";

    private static final Integer SESSION_TIMEOUT = 30 * 1000;

    private static ZooKeeper zooKeeper = null;

    private static CountDownLatch COUNT_DOWN_LATCH = new CountDownLatch(1);

    private Watcher watcher = new Watcher() {
        @Override
        public void process(WatchedEvent watchedEvent) {
            if (watchedEvent.getType() == Event.EventType.None
            && watchedEvent.getState() == Event.KeeperState.SyncConnected){
                COUNT_DOWN_LATCH.countDown();
                log.info("连接已建立");
            }
        }
    };



    @Before
    public void initConnect(){
        try {
            zooKeeper = new ZooKeeper(getConnectStr(),getSessionTimeOut(),watcher);
            log.info("连接中...");
            COUNT_DOWN_LATCH.await();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }


    @After
    public void await(){
        try {
            TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }




    public static ZooKeeper getZooKeeper(){
        return zooKeeper;
    }

    private String getConnectStr(){
        return CONNECT_STR;
    }


    private int getSessionTimeOut(){
        return SESSION_TIMEOUT;
    }


}
