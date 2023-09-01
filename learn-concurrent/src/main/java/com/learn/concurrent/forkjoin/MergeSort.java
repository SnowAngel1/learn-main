package com.learn.concurrent.forkjoin;




import com.learn.concurrent.util.RandomIntArrayUtil;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;

/**
 * @author ChenYP
 * 归并排序
 */

public class MergeSort {

    private final int[] arrayToSort; //要排序的数组

    private final int threshold; // 最小拆分的阈值，低于此阈值不再进行拆分


    public MergeSort(final int[] arrayToSort, final int threshold) {
        this.arrayToSort = arrayToSort;
        this.threshold = threshold;
    }


    public int[] mergeSort() {
        return mergeSort(arrayToSort, threshold);
    }

    private static int[] mergeSort(final int[] arrayToSort, int threshold) {
        // 拆分后的数组长度小于阈值，直接进行排序
        if (arrayToSort.length < threshold) {
            // 调用JDK提供的排序方法
            Arrays.sort(arrayToSort);
            return arrayToSort;
        }

        // 获取数组中间数值
        int midpoint = arrayToSort.length / 2;
        // 对数组进行拆分
        // 左边的数组
        int[] leftArray = Arrays.copyOfRange(arrayToSort, 0, midpoint);
        // 右边的数组
        int[] rightArray = Arrays.copyOfRange(arrayToSort, midpoint, arrayToSort.length);
        //     递归调用
        leftArray = mergeSort(leftArray, threshold);
        rightArray = mergeSort(rightArray, threshold);
        return merge(leftArray,rightArray);

    }

    public static int[] merge(final int[] leftArray, final int[] rightArray) {
        // 定义用于合并结果的数组
        int[] mergeArray = new int[leftArray.length + rightArray.length];
        int mergeArrayPos = 0;
        // 利用双指针进行两个数的比较
        int leftArrayPos = 0;

        int rightArrayPos = 0;

        while (leftArrayPos < leftArray.length && rightArrayPos < rightArray.length){
            if (leftArray[leftArrayPos] <= rightArray[rightArrayPos]){
                mergeArray[mergeArrayPos] = leftArray[leftArrayPos];
                leftArrayPos++;
            }else {
                mergeArray[mergeArrayPos] = rightArray[rightArrayPos];
                rightArrayPos++;
            }
            mergeArrayPos++;
        }
        while (leftArrayPos < leftArray.length){
            mergeArray[mergeArrayPos] = leftArray[leftArrayPos];
            leftArrayPos++;
            mergeArrayPos++;
        }
        while (rightArrayPos < rightArray.length){
            mergeArray[mergeArrayPos] = rightArray[rightArrayPos];
            rightArrayPos++;
            mergeArrayPos++;
        }
        return mergeArray;
    }

    public static void main(String[] args) {
        int[] arraySortByMergeSort = RandomIntArrayUtil.buildRandomIntArray(20000000);

        //生成测试数组  用于forkJoin排序
        int[] arrayToSortByForkJoin = Arrays.copyOf(arraySortByMergeSort, arraySortByMergeSort.length);

        // 获取处理器数量
        int processors = Runtime.getRuntime().availableProcessors();

        MergeSort mergeSort = new MergeSort(arraySortByMergeSort, processors);
        long startTime = System.nanoTime();
        mergeSort.mergeSort();
        long endTime = System.nanoTime() - startTime;
        System.out.println("单线程归并排序时间" + (endTime / (1000f * 1000f) + "毫秒"));

        //利用forkJoin排序
        ForkJoinPoolDemo mergeSortTask = new ForkJoinPoolDemo(arrayToSortByForkJoin, processors);
        //构建forkJoin线程池
        ForkJoinPool forkJoinPool = new ForkJoinPool(processors);
        startTime = System.nanoTime();
        //执行排序任务
        forkJoinPool.invoke(mergeSortTask);
        endTime = System.nanoTime()-startTime;
        System.out.println("forkJoin排序时间: "+(endTime/(1000f*1000f))+"毫秒");
    }


}
