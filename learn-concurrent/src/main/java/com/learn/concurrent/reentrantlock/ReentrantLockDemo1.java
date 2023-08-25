package com.learn.concurrent.reentrantlock;

import com.learn.concurrent.lock.MyLock;


public class ReentrantLockDemo1 {

    // private static final Lock LOCK = new ReentrantLock();

    // 使用自己实现的lock
    private static final MyLock LOCK = new MyLock();

    private static int count;


    private static void incrInt() {
        LOCK.lock();
        try {
            count++;
        } finally {
            LOCK.unlock();
        }
    }


    public static void main(String[] args) throws InterruptedException {

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i < 5000; i++) {
                    incrInt();
                }

            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i < 5000; i++) {
                    incrInt();
                }
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("count = " + count);


    }


}
