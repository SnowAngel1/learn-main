package com.thread;

/**
 * @author 江南
 * @date 2023/5/17
 * 三个线程交替打印1-100数字
 */
public class MyThread {
    public static void main(String[] args) {
        Object lock = new Object();
        final int[] i = {1};
        Thread thread1 = new Thread(
                () -> {
                    while (true) {
                        synchronized (lock) {
                            if (i[0] > 100) break;
                            if ((i[0] + 2) % 3 == 0) {
                                System.out.println(Thread.currentThread().getName() +
                                        "打印" + i[0]);
                                i[0]++;
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
        );
        thread1.setName("线程1");
        thread1.start();

        Thread thread2 = new Thread(
                () -> {
                    while (true) {
                        synchronized (lock) {
                            if (i[0] > 100) break;
                            if ((i[0] + 1) % 3 == 0) {
                                System.out.println(Thread.currentThread().getName() +
                                        "打印" + i[0]);
                                i[0]++;
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
        );
        thread2.setName("线程2");
        thread2.start();

        Thread thread3 = new Thread(
                () -> {
                    while (true) {
                        synchronized (lock) {
                            if (i[0] > 100) break;
                            if (i[0] % 3 == 0) {
                                System.out.println(Thread.currentThread().getName() +
                                        "打印" + i[0]);
                                i[0]++;
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
        );
        thread3.setName("线程3");
        thread3.start();
    }

}
