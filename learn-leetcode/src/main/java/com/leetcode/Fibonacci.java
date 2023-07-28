package com.leetcode;

/**
 * @author：江南
 * @Classname Fibonacci
 * @Description 递归实现斐波那契   1 1 2 3 5 8 13 (前两位数的和==后一位)
 * @Date 2022/6/19 21:49
 */
public class Fibonacci {

    private static int data[];

    public static void main(String[] args) {
       /* for (int i = 1; i <= 45; i++) {
            long start = System.currentTimeMillis();
            System.out.println(i + ":" + fab(i) + "所花费时间为" + (System.currentTimeMillis() - start) + "ms");

        }*/
         for (int i = 1; i <= 45; i++) {
            long start = System.currentTimeMillis();
            System.out.println(i + ":" + noFribo(i) + "所花费时间为" + (System.currentTimeMillis() - start) + "ms");

        }
        System.out.println("=================================是的是的===");
        System.out.println("加了缓存之后");
        data = new int[46];
        for (int i = 1; i <= 45; i++) {
            long start = System.currentTimeMillis();
            System.out.println(i + ":" + fab2(i) + "所花费时间为" + (System.currentTimeMillis() - start) + "ms");

        }



    }
    public static int fab(int n){
        if (n <=2){
            return 1;
        }
        return fab(n - 1) + fab(n - 2);
    }


    public static int fab2(int n){
        if (n <= 2) return 1;
        if (data[n] >0){
            return data[n];
        }
        int res = fab2(n - 1) + fab2(n - 2);
        data[n] = res;
        return res;
    }



    public static int noFribo(int n){
        if(n <=2) return 1;
        int a = 1;
        int b = 1;
        int c = 0;
        for (int i = 3; i <= n; i++) {
            c = a+ b;
            a = b;
            b = c;
        }
        return c;

    }
}
