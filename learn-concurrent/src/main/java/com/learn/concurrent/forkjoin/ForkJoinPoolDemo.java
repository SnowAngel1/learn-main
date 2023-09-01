package com.learn.concurrent.forkjoin;

import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

/**
 * @author ChenYP
 * 适用于计算密集型的线程池
 */

public class ForkJoinPoolDemo extends RecursiveAction {


    private final int threshold; //拆分的阈值，低于此阈值就不再进行拆分
    private int[] arrayToSort; //要排序的数组

    public ForkJoinPoolDemo(final int[] arrayToSort, final int threshold) {
        this.arrayToSort = arrayToSort;
        this.threshold = threshold;
    }

    @Override
    protected void compute() {
        //拆分后的数组长度小于阈值，直接进行排序
        if (arrayToSort.length <= threshold) {
            // 调用jdk提供的排序方法
            Arrays.sort(arrayToSort);
            return;
        }

        // 对数组进行拆分
        int midpoint = arrayToSort.length / 2;
        int[] leftArray = Arrays.copyOfRange(arrayToSort, 0, midpoint);
        int[] rightArray = Arrays.copyOfRange(arrayToSort, midpoint, arrayToSort.length);

        ForkJoinPoolDemo leftTask = new ForkJoinPoolDemo(leftArray, threshold);
        ForkJoinPoolDemo rightTask = new ForkJoinPoolDemo(rightArray, threshold);

        //调用任务
        invokeAll(leftTask, rightTask);

        // 合并排序结果
        arrayToSort = MergeSort.merge(leftTask.getSortedArray(), rightTask.getSortedArray());
    }

    public int[] getSortedArray() {
        return arrayToSort;
    }
}
