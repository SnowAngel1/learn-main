package com.leetcode;

/**
 * @author：江南
 * @Classname ProductExceptSelf
 * @Description 除自身以外数组的乘积  https://leetcode.cn/problems/product-of-array-except-self/
 * @Date 2022/5/15 21:31
 */
public class ProductExceptSelf {
    public static void main(String[] args) {

        int[] ints = productExceptSelf2(new int[]{1, 2, 3, 4});
        for (int i = 0; i < ints.length; i++) {
            System.out.println(ints[i]);
        }
    }

    /***
     * 思路一：使用左右乘积，从左边开始，算出第i个元素左边的所有乘积；再从右边开始算出第i位置右边元素的乘积。最后将左右两边的乘积相乘，得出最后结果
     * @param nums
     * @return
     */
    private static int[] productExceptSelf(int[] nums){
        int length = nums.length;

        int[] L = new int[length];
        int[] R = new int[length];


        int[] answer = new int[length];

        L[0] = 1;
        for (int i = 1; i < length; i++) {
            L[i] = nums[i -1] * L[i -1];
        }


        R[length -1] = 1;
        for (int i = length -2 ; i >= 0; i--) {
            R[i] = nums[i + 1] * R[i + 1];
        }

        for (int i = 0; i < length; i++) {
            answer[i] = L[i] * R[i];
        }
        return answer;
    }

    /***
     * 思路2：将第一种方法优化。其实我们可以利用answer这个数组，第一个循环将左边元素的乘积放入。第二个循环，将右元素的乘积乘以数组中左边的乘积
     * @param nums
     * @return
     */
    private static int[] productExceptSelf2(int[] nums){

        int length = nums.length;
        //answer[i] 表示索引i左侧所有元素的乘积
        int[] answer = new int[length];
        //因为索引0的元素左侧没有元素，所以answer[0] = 1
        answer[0] = 1;
        for (int i = 1; i < length; i++) {
            answer[i] = nums[i -1] * answer[i -1];
        }
        //R 为右侧所有元素的乘积
        //刚开始右边没有怨怒，所以R为1
        int R = 1;
        for (int i = length -1; i >=0; i--) {
            answer[i] = answer[i] * R;
            //R需要包含右边所有的乘积。所以计算下一个结果时，需要将当前值乘到R上
            R *= nums[i];
        }
        return answer;

    }


}
