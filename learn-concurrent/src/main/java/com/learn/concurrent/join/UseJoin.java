package com.learn.concurrent.join;

/**
 * @author ChenYP
 * 使用join()方法，让线程顺序执行，其实就是使线程串行化执行
 */

public class UseJoin {

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
            System.out.println(Thread.currentThread().getName() + "Girl 打饭完成");
        }
    }

    static class Boy implements Runnable {
        @Override
        public void run() {
            System.out.println("Boy 排队打饭");
            System.out.println(Thread.currentThread().getName() + "Boy 打饭完成");
        }
    }


    public static void main(String[] args) throws InterruptedException {

        Thread me = Thread.currentThread();



        // 男孩线程
        Boy boy = new Boy();
        Thread boyThread = new Thread(boy, "Boy");

        // 女孩线程
        Girl girl = new Girl(boyThread);
        Thread girlThread = new Thread(girl, "Girl");


        girlThread.start();
        boyThread.start();

        // 主线程等待 女孩线程执行完成
        girlThread.join();

        System.out.println(me.getName() + "开始打饭");
        System.out.println(me.getName() + "打饭完成");


    }
}
