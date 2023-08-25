package com.learn.concurrent;

/**
 * @author ChenYP
 * 守护线程和非守护线程
 * 将线程使用useThread.setDaemon(true);设置为true即编程守护线程。守护线程会随着主线程的终止而停止
 */

public class DaemonThread {


    private static class UseThread extends Thread{
        @Override
        public void run() {

            while (true){
                System.out.println("I am a " + Thread.currentThread().getName());
               /* try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }*/
            }
        }
    }



    public static void main(String[] args) {
        UseThread useThread = new UseThread();
        useThread.setDaemon(true);
        useThread.start();
        System.out.println("main end");
    }
}
