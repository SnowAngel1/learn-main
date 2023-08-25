package com.learn.concurrent.dl;

/**
 * @author ChenYP
 * 不自知的死锁，将锁对象按照参数传递
 */

public class DynDeadLock {
    private static Object lock1 = new Object();

    private static Object lock2 = new Object();


    private static void baseMethod(Object o1,Object o2) throws InterruptedException {
        String threadName = Thread.currentThread().getName();
        synchronized (o1){
            System.out.println(threadName + "get first");
            Thread.sleep(100);
            synchronized (o2){
                System.out.println(threadName + "get second");
            }
        }
    }

    private static class SonThread extends Thread{

        private String name;

        public SonThread(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            Thread.currentThread().setName(name);
            try {
                baseMethod(lock1,lock2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        SonThread sonThread = new SonThread("sonThread");
        Thread.currentThread().setName("parentThread");
        sonThread.start();
        // baseMethod(lock2,lock1); // 会产生死锁
        baseMethod(lock1,lock2); // 不会产生死锁
    }

}
