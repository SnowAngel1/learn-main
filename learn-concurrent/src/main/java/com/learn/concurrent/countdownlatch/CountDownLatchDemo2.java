package com.learn.concurrent.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author ChenYP
 * 使用CountDownLatch实现子任务执行，最后结果进行汇总
 */

public class CountDownLatchDemo2 {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(1000 + ThreadLocalRandom.current().nextInt(2000));
                System.out.println("子任务开始执行" + i + "执行完成");
                countDownLatch.countDown();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        countDownLatch.await();
        System.out.println("子任务执行完成，进行结果汇总");
    }
}
