package com.learn.concurrent.join;

import com.learn.concurrent.tools.SleepTools;

/**
 * @author ChenYP
 * 不使用join方法，线程的执行顺序是不固定的
 */

public class NoUseJoin {

    static class Girl implements Runnable {

        private Thread thread;

        public Girl() {
        }

        public Girl(Thread thread) {
            this.thread = thread;
        }

        @Override
        public void run() {
            System.out.println("Girl 排队打饭");
            if (thread != null){
                try {
                    thread.join();
                } catch (InterruptedException e) {
                }
            }


            System.out.println(Thread.currentThread().getName() + "打饭完成");
        }
    }

    static class Boy implements Runnable {
        @Override
        public void run() {
            System.out.println("Boy 排队打饭");
            System.out.println(Thread.currentThread().getName() + "打饭完成");
        }
    }


    public static void main(String[] args) {

        Thread me = Thread.currentThread();

        // 男孩线程
        Boy boy = new Boy();
        Thread boyThread = new Thread(boy, "Boy");

        // 女孩线程
        Girl girl = new Girl();
        Thread girlThread = new Thread(girl, "Girl");



        girlThread.start();
        boyThread.start();
        System.out.println(me.getName()+ " 开始打饭");
        SleepTools.second(2);
        System.out.println(me.getName() + " 打饭完成");


    }
}




















