package com.learn.concurrent.dl;

/**
 * @author ChenYP
 * 使用锁排序解决死锁问题
 */

public class SafeOperate {
    private static Object lock1 = new Object();

    private static Object lock2 = new Object();

    private static Object lock3 = new Object();





    private void transLock(Object lock1,Object lock2) throws InterruptedException {

        int lock1HashCode = System.identityHashCode(lock1);

        int lock2HashCode = System.identityHashCode(lock2);

        if (lock1HashCode < lock2HashCode){
            synchronized (lock1){
                System.out.println(Thread.currentThread().getName() + "get" + lock1);
                Thread.sleep(100);
                synchronized (lock2){
                    System.out.println(Thread.currentThread().getName() + "get" + lock2);
                }
            }
        }else if (lock2HashCode > lock1HashCode){
            synchronized (lock2){
                System.out.println(Thread.currentThread().getName() + "get" + lock2);
                Thread.sleep(100);
                synchronized (lock1){
                    System.out.println(Thread.currentThread().getName() + "get" + lock1);
                }
            }
        }else {
            synchronized (lock3){
                synchronized (lock1){
                    System.out.println(Thread.currentThread().getName() + "get" + lock1);
                    Thread.sleep(100);
                    synchronized (lock2){
                        System.out.println(Thread.currentThread().getName() + "get" + lock2);
                    }
                }
            }
        }

    }



}
