package com.learn.concurrent.reentrantlock;


import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ChenYP
 * 模拟多线程 ReentransLock公平锁、非公平锁
 * 公平锁：线程在获取锁时，如果有其他线程排队则加到最后等待获取锁
 * 非公平锁：线程在第一次进来时，不管有没有线程排队等待，都尝试获取锁如果获取锁成功了则执行，否则去排队等待
 */

public class ReentrantLockDemo3 {

    public static void main(String[] args) throws InterruptedException {

        ReentrantLock lock = new ReentrantLock();
        for (int i = 0; i < 500; i++) {
            new Thread(() -> {
                lock.lock();

                try {
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "running...");
                } finally {
                    lock.unlock();
                }
            },"线程——" + i).start();
        }

        Thread.sleep(500);

        for (int i = 0; i < 500; i++) {
            new Thread(() -> {
                lock.lock();

                try {
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "running...");
                } finally {
                    lock.unlock();
                }
            },"强行插入——" + i).start();
        }

    }


}
