package com.learn.concurrent.threadquestions;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 江南
 * @date 2023/5/17
 * 使用ReentrantLock实现多线程交替打印1-100
 */
public class MyThread2 implements Runnable {
    private final int no;

    private static final  ReentrantLock LOCK = new ReentrantLock();

    private static final Condition CONDITION = LOCK.newCondition();

    private static int count;

    public MyThread2(int no) {
        this.no = no;
    }


    @Override
    public void run() {
        while (true) {
            LOCK.lock();
            try {
                if (count > 100) {
                    break;
                } else {
                    if (count % 3 == this.no) {
                        System.out.println("线程" + this.no + "打印-->" + count);
                        count++;
                    } else {
                        try {
                            CONDITION.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                CONDITION.signalAll();
            } finally {
                LOCK.unlock();
            }
        }
    }
}
