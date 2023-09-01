package com.learn.concurrent.sync;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ChenYP
 * Synchronized非公平性的验证
 */
@Slf4j
public class SyncModeDemo {

    private static final Object LOCK = new Object();

    public static void main(String[] args) throws InterruptedException {
        SyncModeDemo syncModeDemo = new SyncModeDemo();
        syncModeDemo.threadA();
        Thread.sleep(100);
        syncModeDemo.threadB();
        Thread.sleep(100);
        syncModeDemo.threadC();
    }


    public void threadA() {
        new Thread(() -> {
            synchronized (LOCK) {
                try {
                    log.info("Thread-A get lock");
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            log.info("A release lock");

        }, "Thread-A").start();
    }


    public void threadB() {
        new Thread(() -> {
            synchronized (LOCK) {
                log.info("Thread-B get lock");
            }
            log.info("B release lock");

        }, "Thread-B").start();
    }


    public void threadC() {
        new Thread(() -> {
            synchronized (LOCK) {
                log.info("Thread-C get lock");
            }
            log.info("C release lock");

        }, "Thread-C").start();
    }


}
