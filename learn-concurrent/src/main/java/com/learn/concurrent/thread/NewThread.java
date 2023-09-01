package com.learn.concurrent.thread;

/**
 * @author ChenYP
 * 新启动线程
 */

public class NewThread {

    private static  class UseThread extends Thread{
        @Override
        public void run() {
            System.out.println("I am extends Thread");
        }
    }

    private static class UseRunnable implements Runnable{
        @Override
        public void run() {
            System.out.println("I am implements Runnable");
        }
    }

    public static void main(String[] args) {
        UseThread useThread = new UseThread();
        useThread.start();
        UseRunnable useRunnable = new UseRunnable();
        new Thread(useRunnable).start();

        System.out.println("main end");
    }

}
