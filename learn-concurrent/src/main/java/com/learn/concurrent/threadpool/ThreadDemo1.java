package com.learn.concurrent.threadpool;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author ChenYP
 */

public class ThreadDemo1 {
    private static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(5);


    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            EXECUTOR_SERVICE.submit(()->{
                try {
                    // 执行任务操作
                    System.out.println(Thread.currentThread().getName() + "正在执行任务。。。");
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }finally {
                    System.out.println(Thread.currentThread().getName() + "任务执行完毕");
                }
            });
        }
        // 停止线程池接收新的任务。但不能强制停止已提交的任务
        EXECUTOR_SERVICE.shutdown();

        // 等待线程池中的任务执行完毕，或者超时时间到达
        boolean termination = EXECUTOR_SERVICE.awaitTermination(3, TimeUnit.SECONDS);
        if (!termination){
            // 如果线程池中还有未执行的任务，则调用线程池的shutdownNow()方法，中断所有正在执行的任务
            // 如果还没有开始执行的任务，则返回未执行的任务列表
            List<Runnable> tasks = EXECUTOR_SERVICE.shutdownNow();
            System.out.println("剩余未执行的任务数：" + tasks.size());
        }


    }
}
