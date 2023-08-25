package com.learn.concurrent.threadLocal;

import com.learn.concurrent.tools.SleepTools;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author ChenYP
 * ThreadLocal 造成内存泄漏
 */

public class ThreadLocalMemoryLeak {

    private static final int TASK_LOOP_SIZE = 500; //500个任务

    final static ThreadPoolExecutor POOL_EXECUTOR = new ThreadPoolExecutor(5,5,1, TimeUnit.MINUTES,new LinkedBlockingDeque<>());

    static class LocalVariable{
        private byte[] a = new byte[1024 * 1024 * 5]; //5M
    }
    ThreadLocal<LocalVariable> localVariableThreadLocal;

    public static void main(String[] args) {
        SleepTools.sleepMs(4000);
        for (int i = 0; i < TASK_LOOP_SIZE; i++) {
            POOL_EXECUTOR.execute(new Runnable() {
                @Override
                public void run() {
                    SleepTools.sleepMs(500);

                    // LocalVariable localVariable = new LocalVariable();
                    ThreadLocalMemoryLeak localMemoryLeak = new ThreadLocalMemoryLeak();
                    localMemoryLeak.localVariableThreadLocal = new ThreadLocal<>();
                    localMemoryLeak.localVariableThreadLocal.set(new LocalVariable());
                    localMemoryLeak.localVariableThreadLocal.remove();

                    System.out.println("use local variable");
                }
            });
            SleepTools.sleepMs(100);
        }
        System.out.println("pool execute over");



    }



}
