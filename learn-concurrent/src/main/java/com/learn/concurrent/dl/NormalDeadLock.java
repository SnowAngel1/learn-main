package com.learn.concurrent.dl;

/**
 * @author ChenYP
 * 模拟死锁
 */

public class NormalDeadLock {

    // 锁1
    private static final Object lock1 = new Object();

    // 锁2
    private static final Object lock2 = new Object();

    // 第一个拿锁的方法
    private static void oneThreadDo() throws InterruptedException {
        String threadName = Thread.currentThread().getName();
        synchronized (lock1){
            System.out.println( threadName + "get lock1");
            Thread.sleep(100);
            synchronized (lock2){
                System.out.println(threadName + "get lock2");
            }
        }
    }

    // 第二个拿锁的方法
    private static void twoThreadDo() throws InterruptedException {
        String threadName = Thread.currentThread().getName();
        synchronized (lock2){
            System.out.println( threadName + "get lock2");
            Thread.sleep(100);
            synchronized (lock1){
                System.out.println(threadName + "get lock1");
            }
        }
    }
    // 子线程
    private static class OneThread extends Thread{

        private String name;

        public OneThread(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            Thread.currentThread().setName(name);
            try {
                oneThreadDo();
            } catch (InterruptedException e) {
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        OneThread oneThread = new OneThread("sonThread");
        // 主线程设置名称
        Thread.currentThread().setName("parentThread");
        new Thread(oneThread).start();
        twoThreadDo();
    }




}
