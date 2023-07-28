package com.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ChenYP
 * @date 2023/7/15 16:40
 * @describe 爬楼梯
 */
public class ClimbingStairs_78 {

    private static Map<Integer,Integer> storeMap = new HashMap<>();


    /**
     * 递归解法
     * 缺点：时间复杂度太高，存在计算重复的数据结果
     * @param n
     * @return
     */
    public static int climbingStairs(int n){
        if (n == 1){
            return 1;
        }
        if (n == 2){
            return 2;
        }
        return climbingStairs(n -1) + climbingStairs(n -2);
    }

    /**
     * 使用hashMap 存放重复计算的结果
     * @param n
     * @return
     */
    public static int climbingStairs1(int n){
        if (n == 1) {
            return 1;
        }
        if (n == 2){
            return 2;
        }
        if (null != storeMap.get(n)){
            return storeMap.get(n);
        }else {
            int res = climbingStairs1(n -1) + climbingStairs1(n -2);
            storeMap.put(n, res);
            return res;
        }

    }



    public static void main(String[] args) {
        //使用递归解决爬楼梯问题
        System.out.println(climbingStairs(2));


    }
}
