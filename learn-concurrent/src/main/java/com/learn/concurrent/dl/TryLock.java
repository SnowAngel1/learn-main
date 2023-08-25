package com.learn.concurrent.dl;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ChenYP
 * 使用ReetransLock 尝试获取锁，来解决死锁
 */

public class TryLock {
    private static Lock lock1 = new ReentrantLock();

    private static Lock lock2 = new ReentrantLock();


    private static void tryLock1() throws InterruptedException {

        Random random = new Random();

        while (true){
            if (lock1.tryLock()){
                System.out.println(Thread.currentThread().getName() + "get lock1");
                try {
                    if (lock2.tryLock()){
                        try {
                            System.out.println(Thread.currentThread().getName() + "get Lock2");
                            System.out.println(Thread.currentThread().getName() + "do work");
                            break;
                        } finally {
                            lock2.unlock();
                        }
                    }
                } finally {
                    lock1.unlock();
                }
            }
        }
        // Thread.sleep(random.nextInt(3));
    }


    private static void tryLock2() throws InterruptedException {

        Random random = new Random();

        while (true){
            if (lock2.tryLock()){
                System.out.println(Thread.currentThread().getName() + "get lock2");
                try {
                    if (lock1.tryLock()){
                        try {
                            System.out.println(Thread.currentThread().getName() + "get lock1");
                            System.out.println(Thread.currentThread().getName() + "do work");
                            break;
                        } finally {
                            lock1.unlock();
                        }
                    }
                } finally {
                    lock2.unlock();
                }
            }
        }
        // Thread.sleep(random.nextInt(3));
    }

    private static class SonThread extends Thread{
        private String name;

        public SonThread(String name){
            this.name = name;
        }

        @Override
        public void run() {
            Thread.currentThread().setName(name);
            try {
                tryLock1();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SonThread sonThread = new SonThread("SonThread");
        Thread.currentThread().setName("Parent Thread");
        sonThread.start();
        tryLock2();
    }




}
