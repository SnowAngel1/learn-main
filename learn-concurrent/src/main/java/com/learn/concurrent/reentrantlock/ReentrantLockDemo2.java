package com.learn.concurrent.reentrantlock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ChenYP
 * 模拟多线程抢票
 */

public class ReentrantLockDemo2 {

    // 总票数
    private static int tickets = 8;

    private static final ReentrantLock LOCK = new ReentrantLock();

    private static CountDownLatch countDownLatch = new CountDownLatch(10);

    public static void buyTickets() {

        LOCK.lock();

        try {
            if (tickets > 0) {
                try {
                    Thread.sleep(10); // 休眠10ms
                } catch (InterruptedException e) {

                }

                System.out.println(Thread.currentThread().getName() + "购买了第" + tickets-- +" 张票");


            } else {
                // 卖完了
                System.out.println("票已经卖完了" + Thread.currentThread().getName() + "抢票失败");
            }
        countDownLatch.countDown();
        } finally {
            LOCK.unlock();
        }
    }


    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    buyTickets(); // 抢票
                }
            },"线程——" + i);
            thread.start();
        }
        countDownLatch.await();
        System.out.println("剩余票数：" + tickets);
    }


}
