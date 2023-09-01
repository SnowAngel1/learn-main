package com.learn.concurrent.util;

import java.util.Random;

/**
 * @author ChenYP
 * 生成随机数组
 */

public class RandomIntArrayUtil {

    public static int[] buildRandomIntArray(final int arraySize){
        // 初始化数组
        int[] arrayToCalculateSumOf = new int[arraySize];
        Random random = new Random();
        for (int i = 0; i < arrayToCalculateSumOf.length; i++) {
            arrayToCalculateSumOf[i] = random.nextInt(100000000);
        }
        return arrayToCalculateSumOf;
    }









}
