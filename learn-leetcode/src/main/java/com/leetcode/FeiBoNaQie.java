package com.leetcode;

/**
 * @author：江南
 * @Classname FeiBoNaQie
 * @Description  递归，斐波那契数列
 * @Date 2022/5/29 23:01
 */
public class FeiBoNaQie {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(i + ":" + feiBo(i));
        }
    }



    public static int feiBo(int n){
        if (n <=2){
            return n;
        }
        return feiBo(n -1)+ feiBo(n - 2);
    }
}
