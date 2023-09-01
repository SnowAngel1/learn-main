package com.learn.concurrent.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * @author ChenYP
 */

public class CountDownLatchDemo4 {

    private static final CountDownLatch COUNT_DOWN_LATCH = new CountDownLatch(1);

    public static void main(String[] args) throws InterruptedException {

    new Thread(new Runnable() {
        @Override
        public void run() {

                System.out.println(Thread.currentThread().getName() + "开始执行");
                System.out.println(Thread.currentThread().getName() + "执行完成");
                COUNT_DOWN_LATCH.countDown();
        }
    },"线程——1").start();



        Thread.currentThread().setName("main 线程等待子线程执行");
        COUNT_DOWN_LATCH.await();
        System.out.println(Thread.currentThread().getName() + "开始执行");



    }
}
