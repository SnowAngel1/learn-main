package com.learn.concurrent.threadquestions;

/**
 * @author 江南
 * @date 2023/5/18
 */
public class ThreadMain {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new MyThread2(0));
        Thread t2 = new Thread(new MyThread2(1));
        Thread t3 = new Thread(new MyThread2(2));
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
    }

}
