package com.learn.concurrent.thread;

import com.learn.concurrent.tools.SleepTools;

/**
 * @author ChenYP
 * 如何安全中断线程
 */

public class EndThread {

    private static class UseThread extends Thread{
        private boolean cancel;

        public UseThread(String name){
            super(name);
        }
        public void setCancel(boolean cancel){
            this.cancel = cancel;
        }


        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + "interrupt flag = " + isInterrupted());
            while (true){
            // while (!isInterrupted()){
            // while (!Thread.interrupted()){ // 判断当前标志位，还会将当前标志位设置为false
                System.out.println(threadName + "is running");
                System.out.println(threadName + "inner interrupt flag = " + isInterrupted());
            }
            // System.out.println(threadName + "inner interrupt flag = " + isInterrupted());
        }
    }




    public static void main(String[] args) {
        UseThread useThread = new UseThread("endName");
        useThread.start();
        SleepTools.second(1);
        useThread.interrupt(); // 中断线程，其实设置线程中的标识位 = true
    }
}
