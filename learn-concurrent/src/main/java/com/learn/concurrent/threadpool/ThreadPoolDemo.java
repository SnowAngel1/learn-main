package com.learn.concurrent.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author ChenYP
 * 线程池，解决线程的不断创建和销毁带来的性能消耗
 * 线程池参数的设置
 *      核心线程数：分为CPU密集型、IO密集型、混合型；CPU密集型 = CPU核数+1；IO密集型= CPU核数 * （线程等待时间 / 线程执行时间 + 1）
 *      最大线程数：一般设置为业务请求最大的访问数
 *      线程存活时间（时间单位）：线程空闲时，在超过设置的超时时间时，线程会被唤醒，如果线程池中的线程数 > 核心线程数，那么通过CAS操作对线程总数进行减减，减成功的线程会自动退出，直到线程池中的线程等于设置的核心线程数
 *      队列长度：根据业务处理的时间进行估计，在线程处理任务时需要进入队列等待线程的执行，所以如果队列太长会导致最后加入的任务等待执行的时间会很长，所以需要对业务执行的时间和业务可以接受的等待时间进行评估
 *      当队列满了，并且达到了最大的线程数，线程池的拒绝策略
 *
 *
 */

public class ThreadPoolDemo {


    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10,200,1000, TimeUnit.SECONDS, new ArrayBlockingQueue(10));
        // 当提交任务时，即使有空闲的线程，线程池的线程总数小于核心线程数，也会新建线程去执行任务，不会复用原来的线程
        executor.execute(()->{
            System.out.println("111");
        });
        // 中断所有线程
        executor.shutdownNow();
        // 中断空闲的线程，需要保留一部分线程去处理队列中的任务
        executor.shutdown();


        /**
         * 使用自定义线程工厂，自定义抛出异常处理
         */
        // ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 200, 60, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(100), new ThreadFactory() {
        //     @Override
        //     public Thread newThread(Runnable r) {
        //
        //
        //        Thread thread = new Thread(r);
        //        thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler(){
        //            @Override
        //            public void uncaughtException(Thread t, Throwable e) {
        //                System.out.println("出错咯");
        //            }
        //        });
        //        return thread;
        //     }
        // });
        //
        // executor.execute(new Runnable() {
        //     @Override
        //     public void run() {
        //         System.out.println("-----");
        //         throw new NullPointerException();
        //     }
        // });



    }
}
