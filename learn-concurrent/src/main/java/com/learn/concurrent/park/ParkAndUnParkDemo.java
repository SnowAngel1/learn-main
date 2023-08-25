package com.learn.concurrent.park;

import java.util.concurrent.locks.LockSupport;

/**
 * @author ChenYP
 *  LockSupport.park();
 *  LockSupport.unpark();
 *  唤醒指定线程
 */

public class ParkAndUnParkDemo {


    public static void main(String[] args) {
        ParkAndUnParkThread parkAndUnParkThread = new ParkAndUnParkThread(Thread.currentThread());
        parkAndUnParkThread.start();

        System.out.println("before park");
        LockSupport.park();
        System.out.println("after park");





    }
}
class ParkAndUnParkThread extends Thread{
    private Thread thread;

    public ParkAndUnParkThread(Thread thread) {
        this.thread = thread;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("before unpark");
        // 释放许可
        LockSupport.unpark(thread);
        System.out.println("after unpark");
    }
}
