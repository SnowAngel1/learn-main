package com.learn.concurrent.threadquestions;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 江南
 * @date 2023/5/17
 * 三个线程交替打印1-100数字
 */
public class MyThread {
    public static void main(String[] args) {
        Object lock = new Object();

        AtomicInteger count = new AtomicInteger(1);
        // 打印1
        Thread thread1 = new Thread(()->{

            while (true){
                synchronized (lock){
                    if (count.get() > 100){
                        break;
                    }else {
                        if ((count.get() + 2) % 3 == 0){
                            System.out.println(Thread.currentThread().getName() + " 打印 " + count.get());
                            count.addAndGet(1);
                            lock.notifyAll();
                        }else {
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }

            }
        });
        thread1.setName("线程1");
        thread1.start();


        // 打印2
        Thread thread2 = new Thread(()->{

            while (true){
                synchronized (lock) {
                    if (count.get() > 100) {
                        break;
                    } else {
                        if ((count.get() + 1) % 3 == 0) {
                            System.out.println(Thread.currentThread().getName() + " 打印 " + count.get());
                            count.addAndGet(1);
                            lock.notifyAll();
                        } else {
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        });
        thread2.setName("线程2");
        thread2.start();


        // 打印2
        Thread thread3 = new Thread(()->{

            while (true){
                synchronized (lock) {
                    if (count.get() > 100) {
                        break;
                    } else {
                        if ((count.get() % 3) == 0) {
                            System.out.println(Thread.currentThread().getName() + " 打印 " + count.get());
                            count.addAndGet(1);
                            lock.notifyAll();
                        } else {
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        });
        thread3.setName("线程3");
        thread3.start();

    }

}
