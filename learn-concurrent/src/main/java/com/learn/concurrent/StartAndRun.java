package com.learn.concurrent;

/**
 * @author ChenYP
 * Start方法与run方法区别：run方法只是一个普通的方法，start方法是开启一个线程，至于为什么我们要实现run方法，是为了start方法在执行时需要绑定一个run方法执行业务代码，run方法可以调用多次，start方法就只能调用一次
 */

public class StartAndRun {



    public static class ThreadRun extends Thread{
        @Override
        public void run() {
            int i = 90;
            while (i > 0){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("I am " + Thread.currentThread().getName() + "and now the i" + i-- );
            }
        }
    }

    public static void main(String[] args) {
        ThreadRun threadRun = new ThreadRun();
        threadRun.setName("threadRun");
        threadRun.start();
        threadRun.run();
        threadRun.run();
    }
}
