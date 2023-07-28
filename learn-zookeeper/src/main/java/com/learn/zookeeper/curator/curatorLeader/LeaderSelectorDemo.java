package com.learn.zookeeper.curator.curatorLeader;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListener;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author ChenYP
 * @date 2023/7/26 22:07
 * @Describe
 */
public class LeaderSelectorDemo {

    private final static String CONNECT_STR = "192.168.17.128:2181";

    /**
     * 异常重试机制
     */
    private static RetryPolicy retryPolicy = new ExponentialBackoffRetry(5 * 1000,10);

    private static CuratorFramework curatorFramework;


    private static CountDownLatch COUNT_DOWN_LATCH = new CountDownLatch(1);




    public static void main(String[] args) throws InterruptedException {
        String appName = System.getProperty("appName");

        curatorFramework = CuratorFrameworkFactory.newClient(CONNECT_STR,retryPolicy);
        curatorFramework.start();
        LeaderSelectorListener listener = new LeaderSelectorListenerAdapter()
        {
            public void takeLeadership(CuratorFramework client) throws Exception {
                System.out.println("I'm leader now . i'm" + appName);
            }
        };

        LeaderSelector selector = new LeaderSelector(curatorFramework, "/cachePreHeat_leader", listener);
        selector.autoRequeue();  // not required, but this is behavior that you will probably expect
        selector.start();


        COUNT_DOWN_LATCH.await();

    }
}
