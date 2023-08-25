package com.learn.concurrent.pool;

import java.sql.Connection;
import java.util.LinkedList;

/**
 * @author ChenYP
 * 连接池实现
 */

public class DBPool {

    /**
     * 容器，存放连接
     */
    private static LinkedList<Connection> pool = new LinkedList<>();

    /**
     * 限制连接池的大小 = 20
     * @param initialSize
     */
    public DBPool(int initialSize) {
        if (initialSize > 0){
            for (int i = 0; i < initialSize; i++) {
                pool.add(SqlConnectImpl.fetchConnection());
            }
        }
    }

    /**
     * 释放连接，通知其他的等待线程
     * @param connection
     */
    public void releaseConnection(Connection connection){
        if (connection != null){
            synchronized (pool){
                // 还回连接
                pool.addLast(connection);
                pool.notifyAll();
            }
        }
    }

    public Connection fetchConnection(long mills) throws InterruptedException {
        synchronized (pool){
            // 永远等待
            if (mills <= 0){
                while (pool.isEmpty()){
                    wait();
                }
                // 返回连接
                return pool.removeFirst();
            }else {
                //超时时刻
                long future = System.currentTimeMillis() + mills;

                // 等待时长
                long remaining = mills;

                while (pool.isEmpty() && remaining >0){
                    pool.wait(remaining);
                    // 唤醒一次，重新计算等待时长
                    remaining = future - System.currentTimeMillis();
                }
                Connection connection = null;
                if (!pool.isEmpty()){
                    connection = pool.removeFirst();
                }
                return connection;

            }
        }
    }
}






















