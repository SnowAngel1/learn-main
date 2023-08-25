package com.learn.concurrent.semaphore;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author ChenYP
 * Semaphore信号量实现限流
 */

public class SemaphoreDemo {

    private static Semaphore semaphore = new Semaphore(2);

    private static Executor executors = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            executors.execute(()->{getProductInfo2();});
        }

    }

    public static String getProductInfo(){
        try {
            semaphore.acquire(); // 申请许可
            System.out.println("请求服务");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            semaphore.release(); // 释放许可
        }
        return "返回商品信息";
    }

    /**
     * 限流
     * @return
     */
    public static String getProductInfo2(){
        try {
            if (!semaphore.tryAcquire()){
                System.out.println("请求被流控了");
                return "请求被流控了";
            }
            System.out.println("请求服务");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            semaphore.release(); // 释放许可
        }
        return "返回商品信息";
    }

}
