package com.leetcode;

import javax.sound.midi.MidiFileFormat;
import java.util.Arrays;

/**
 * @author ChenYP
 * @date 2023/7/15 17:27
 * @describe 合并两个有序数组
 */
public class MergeTwoArrays {
    public static void main(String[] args) {
        int[] merge = merge3(new int[]{1, 2, 3, 0, 0, 0}, 3, new int[]{2, 5, 6}, 3);
        for (int i : merge) {

            System.out.println(i);
        }
    }

    /**
     * 使用JDK提供的方法，缺点是：慢
     *
     * @param nums1 数组1
     * @param m     nums的 长度
     * @param nums2 数组2
     * @param n     num2的长度
     * @return 返回合并后的数组
     */
    public static int[] merge1(int[] nums1, int m, int[] nums2, int n) {
        for (int i = 0; i < n; i++) {
            nums1[m + i] = nums2[i];
        }
        Arrays.sort(nums1);
        return nums1;
    }

    /**
     * 使用JDK提供的方法，缺点是：慢
     *
     * @param nums1 数组1
     * @param m     nums的 长度
     * @param nums2 数组2
     * @param n     num2的长度
     * @return 返回合并后的数组
     */
    public static int[] merge2(int[] nums1, int m, int[] nums2, int n) {
        // 临时数组，存放排序后的数据
        int[] temp = new int[nums1.length];
        for (int nums1Index = 0, nums2Index = 0, tempIndex = 0; tempIndex < nums1.length; tempIndex++) {
            //数组长度：nums1 > nums2
           if (nums1Index >= m){
               //nums1有效数据取完，将nums2的全部数据放到临时数组
               temp[tempIndex] = nums2[nums2Index++];
           } else if (nums2Index >= n) {
               //nums2有效数据取完，将nums1的全部数据放到临时数组
               temp[tempIndex] = nums1[nums1Index++];
           }else if (nums1[nums1Index] < nums2[nums2Index]){
               //nums1数组的元素小于nums2数组中的元素
               temp[tempIndex] = nums1[nums1Index++];
           }else {
               //nums2数组的元素小于nuns数组中的元素
               temp[tempIndex] = nums2[nums2Index++];
           }
        }
        // 最后返回 nums1数组
        for (int i = 0; i < nums1.length; i++) {
            nums1[i] = temp[i];
        }
        return nums1;
    }


    public static int[] merge3(int[] nums1, int m, int[] nums2, int n){
        for (int index = nums1.length -1,nums1Index = m-1, nums2Index = n-1; index >= 0; index--) {
           if (nums1Index < 0){
               nums1[index] = nums2[nums2Index--];
           } else if (nums2Index < 0) {
                break;
           } else if (nums1[nums1Index] < nums2[nums2Index]) {
               nums1[index] = nums2[nums2Index--];
           }else {
               nums1[index] = nums1[nums1Index--];
           }
        }
        return nums1;

    }


}
