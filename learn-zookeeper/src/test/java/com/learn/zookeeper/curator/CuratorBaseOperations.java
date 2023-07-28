package com.learn.zookeeper.curator;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author ChenYP
 * @date 2023/7/26 10:24
 * @Describe
 */
@Slf4j
public class CuratorBaseOperations extends CuratorStandaloneBase{


    /**
     * 递归创建子节点
     */
    @Test
    public void testCreateWithParent() throws Exception {
        CuratorFramework curatorFramework = getCuratorFramework();

        String path = "/node-parent/sub-node-1";
        curatorFramework.create().creatingParentsIfNeeded().forPath(path);
        log.info("curator create node:{} successfully",path);
    }

    /**
     * Protection 保护模式，防止异常原因，导致的僵尸节点
     * @throws Exception
     */
    @Test
    public void testCreate() throws Exception {
        CuratorFramework curatorFramework = getCuratorFramework();
        String path = curatorFramework.create()
                .withProtection()
                .withMode(CreateMode.EPHEMERAL_SEQUENTIAL)
                .forPath("/curator node","some-data".getBytes());
        log.info("curator create node:{} successfully",path);
    }

    @Test
    public void testThreadPool() throws Exception {
        CuratorFramework curatorFramework = getCuratorFramework();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        String zkNode = "/zk-node";

        curatorFramework.getData().inBackground((client,event)->{
           log.info("background:{}",event);
        },executorService).forPath(zkNode);


    }
}
