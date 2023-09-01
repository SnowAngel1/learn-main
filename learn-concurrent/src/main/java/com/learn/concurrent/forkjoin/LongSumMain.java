package com.learn.concurrent.forkjoin;

import com.learn.concurrent.util.RandomIntArrayUtil;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/**
 * @author ChenYP
 */

public class LongSumMain {

    // 获取CPU核数
    static final int NUM_CPU = Runtime.getRuntime().availableProcessors();
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 准备数组
        int[] array = RandomIntArrayUtil.buildRandomIntArray(1000000000);
        // 构建任务
        LongSum longSum = new LongSum(array,0,array.length);
        // 构建Fork Join
        ForkJoinPool forkJoinPool = new ForkJoinPool(NUM_CPU);
        // 提交任务计算数组总和
        ForkJoinTask<Long> submit = forkJoinPool.submit(longSum);
        Long sum = submit.get();
        System.out.println("数组总和:" + sum);
        forkJoinPool.shutdown();
    }
}
