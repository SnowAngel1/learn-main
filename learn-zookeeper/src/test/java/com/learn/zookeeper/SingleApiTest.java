package com.learn.zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;

/**
 * @author ChenYP
 * @date 2023/7/25 17:00
 * @Describe
 */
@Slf4j
public class SingleApiTest extends SingleBaseConnectTest{

    private String first_node = "/first_node";

    @Test
    public void testCreate() throws InterruptedException, KeeperException {
        ZooKeeper zooKeeper = getZooKeeper();
        String s = zooKeeper.create(first_node,"first".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
        log.info("Create:{}",s);
    }


    /**
     * watcher监听数据变化
     */
    @Test
    public void testWatchGetData(){
        Watcher watcher = new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                if (watchedEvent.getPath().equals(first_node)
                    && watchedEvent.getType() == Event.EventType.NodeDataChanged
                        && watchedEvent.getState() == Event.KeeperState.SyncConnected){
                    try {
                        byte[] newData = getZooKeeper().getData(first_node, this, null);
                        log.info("PATH:{}数据发生变化:{}",first_node,new String(newData));
                    } catch (KeeperException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

            }
        };
        try {
            byte[] oldData = getZooKeeper().getData(first_node, watcher, null);
            log.info("原始数据:{}",new String(oldData));
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        }
    }


    /**
     * 基于乐观锁实现设置数据
     */
    @Test
    public void testSetDataVersion() throws InterruptedException, KeeperException {
        ZooKeeper zooKeeper = getZooKeeper();
        Stat stat = new Stat();
        zooKeeper.getData(first_node,false,stat);
        int dataVersion = stat.getVersion();
        zooKeeper.setData(first_node,"second".getBytes(),0);
    }


    @Test
    public void testDelete() throws InterruptedException, KeeperException {
        ZooKeeper zooKeeper = getZooKeeper();
        //-1 代表匹配所有版本，直接删除
        //任意大于-1的代表可以指定数据版本删除
        zooKeeper.delete(first_node,-1);
    }


    /**
     * 通过回调函数，异步执行
     */
    @Test
    public void  testAsync(){
        ZooKeeper zooKeeper = getZooKeeper();
        zooKeeper.getData("/test",false,(rc,path,ctx,data,stat)->{
            Thread thread = Thread.currentThread();
            log.info("Thread Name :{},rc:{}.path:{},ctx:{},data:{},stat:{}",thread.getName(),rc,path,ctx,data,stat);
        },"test");

        log.info("over");
    }



}
