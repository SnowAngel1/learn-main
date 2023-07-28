package com.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author：江南
 * @Classname TwoSum
 * @Description 两数之和  https://leetcode-cn.com/problems/two-sum/
 * @Date 2022/4/11 22:29
 */
public class TwoSum {
    public static void main(String[] args) {
        for (int i : twoSum1(new int[]{1, 3, 4, 2}, 6)) {
            System.out.println(i);
        }
    }

    private static int[] twoSum1(int[] nums, int target) {
        int length = nums.length;

        for (int i = 0; i < nums.length; i++) {
            for (int j = length - 1; j > 0; j--) {
                if (i == j) {
                    continue;
                }
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        throw new RuntimeException("没有目标值");
    }

    /**
     * 使用Map计算两数之和
     *
     * @param nums   数组
     * @param target 目标值
     * @return 目标值 两数相加
     */
    private static int[] twoNum2(int[] nums, int target) {
       Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)){
                return new int[]{map.get(complement),i};
            }
            map.put(nums[i],i);
        }
        throw new RuntimeException("没有目标值");
    }
}
