package com.learn.concurrent.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author ChenYP
 * 演示Future等的使用
 */

public class UseFuture {


    private static class UseCallable implements Callable<Integer> {
        private int sum;

        @Override
        public Integer call() throws Exception {
            for (int i = 0; i < 5000; i++) {
                // 判断当前线程的中断标志位
                if (Thread.currentThread().isInterrupted()){
                    System.out.println("Callable子线程计算任务中断");
                    return null;
                }
                sum += i;
                System.out.println("sum" + sum);
            }
            System.out.println("Callable子线程计算结束！，结果为" +  sum);
            return sum;
        }
    }






    public static void main(String[] args) {

        UseCallable useCallable = new UseCallable();

        FutureTask<Integer> futureTask = new FutureTask(useCallable);
        Thread thread = new Thread(futureTask);
        thread.start();
        Thread.interrupted();

        try {
            Integer i = futureTask.get();
            System.out.println(i);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }


    }


}
