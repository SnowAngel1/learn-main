package com.leetcode;

/**
 * @author：江南
 * @Classname MaxProduct
 * @Description 乘积最大子数组   https://leetcode-cn.com/problems/maximum-product-subarray/
 * @Date 2022/5/4 21:31
 * 思路：动态规划
 */
public class MaxProduct {
    public static void main(String[] args) {
        System.out.println(maxProduct(new int[]{2, 3, -2, 4}));
    }


    public static int maxProduct(int[] nums){
        int max = nums[0];
        int min = nums[0];
        int res = nums[0];

        for(int i=1;i<nums.length;i++){
            int p1 = max*nums[i];
            int p2 = min*nums[i];
            max = Math.max(nums[i],Math.max(p1,p2));
            min = Math.min(nums[i],Math.min(p1,p2));
            res = Math.max(res,max);

        }

        return res;

    }
}
