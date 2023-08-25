package com.learn.concurrent.threadLocal;

/**
 * @author ChenYP
 *  ThreadLocal 使用
 */

public class UseThreadLocal {
     static ThreadLocal<String> threadLocal1 = new ThreadLocal<>();
     static ThreadLocal<Integer> threadLocal2 = new ThreadLocal<>();

     // static MyThreadLocal<String> myThreadLocal = new MyThreadLocal<>();


    private static class TestThreadLocal extends Thread{

        private int id;

        public TestThreadLocal(int id){
            this.id = id;
        }

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();

            threadLocal1.set("线程_" + id);
            if (id == 2){
                threadLocal2.set(id);
            }


            System.out.println(threadName + " : " + threadLocal1.get());
            System.out.println(threadName + " : " + threadLocal2.get());
        }
    }


    public void startThreadArray(){
        Thread[] threads  = new Thread[3];
        for (int i = 0; i < threads.length; i++) {
            new TestThreadLocal(i).start();
        }
    }

    public static void main(String[] args) {
        UseThreadLocal useThreadLocal = new UseThreadLocal();
        useThreadLocal.startThreadArray();


    }

}
