package com.learn.zookeeper;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author ChenYP
 * @date 2023/7/25 15:58
 * @Describe  使用zookeeper提供的客户端连接zookeeper
 */
@Slf4j
public class ConfigCenter {

    /**
     * zookeeper 服务地址
     */
    private static final  String CONNECT_STR = "127.0.0.1:2181";

    /**
     * session 超时时间
     */
    private static final  Integer SESSION_TIMEOUT = 30 * 100;

    /**
     * zookeeper 客户端
     */
    private static ZooKeeper zooKeeper;

    private static final CountDownLatch COUNT_DOWN_LATCH = new CountDownLatch(1);


    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        zooKeeper = new ZooKeeper(CONNECT_STR, SESSION_TIMEOUT, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                if (watchedEvent.getType() == Event.EventType.None
                        && watchedEvent.getState() == Event.KeeperState.SyncConnected){
                    log.info("zookeeper连接已建立");
                    COUNT_DOWN_LATCH.countDown();
                }
            }
        });
        COUNT_DOWN_LATCH.await();

        String path = "/myconfig";

        MyConfig myConfig = new MyConfig();
        myConfig.setKey("anyKey");
        myConfig.setName("anyName");

        ObjectMapper objectMapper = new ObjectMapper();

        byte[] jsonBytes = objectMapper.writeValueAsBytes(myConfig);


        String s = zooKeeper.create(path, jsonBytes, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        Watcher watcher = new Watcher() {
            @SneakyThrows
            @Override
            public void process(WatchedEvent watchedEvent) {
                    if (watchedEvent.getType() == Event.EventType.NodeDataChanged
                    && watchedEvent.getPath() != null && watchedEvent.getPath().equals(path)){
                        log.info("PATH:{} 发生了数据变化",watchedEvent.getPath());
                        byte[] newDate = zooKeeper.getData(path, this, null);
                        MyConfig newData = objectMapper.readValue(newDate, MyConfig.class);
                        log.info("数据发生了变化：{}",newData);

                    }
            }
        };

        byte[] oldDataByte = zooKeeper.getData(path, watcher, null);
        MyConfig oldData = objectMapper.readValue(oldDataByte, MyConfig.class);
        log.info("原始数据:{}", oldData);


        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
    }
}
