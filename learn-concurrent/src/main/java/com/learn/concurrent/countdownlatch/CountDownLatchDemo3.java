package com.learn.concurrent.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * @author ChenYP
 * 线程一起执行
 */

public class CountDownLatchDemo3 {
    private static final CountDownLatch COUNT_DOWN_LATCH = new CountDownLatch(5);


    private static final CountDownLatch begin = new CountDownLatch(1);

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(Thread.currentThread().getName() + "准备好了");
                        begin.await();
                        System.out.println(Thread.currentThread().getName() + "执行完成");
                        COUNT_DOWN_LATCH.countDown();

                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                }
            },"线程——" + i).start();


        }
        Thread.sleep(1000);
        begin.countDown();

    }



}
