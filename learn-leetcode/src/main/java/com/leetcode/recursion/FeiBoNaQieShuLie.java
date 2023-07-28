package com.leetcode.recursion;

/**
 * @author：江南
 * @Description 斐波那契数列
 * @since 1.0.0
 */
public class FeiBoNaQieShuLie {

    private static int[] data;

    public static void main(String[] args) {

        /*for (int i = 1; i <= 45; i++) {
            long start = System.currentTimeMillis();
            System.out.println(i + " : " + feiBo(i) + " 所花费时间 " + (System.currentTimeMillis() - start) + "ms");
        }*/

        /*for (int i = 1; i <= 45; i++) {
            long start = System.currentTimeMillis();
            System.out.println(i + " : " + feiBo2(i) + " 所花费时间 " + (System.currentTimeMillis() - start) + "ms");
        }*/

        data = new int[46];
       /* for (int i = 1; i <= 45; i++) {
            long start = System.currentTimeMillis();
            System.out.println(i + " : " + feiBo3(i) + " 所花费时间 " + (System.currentTimeMillis() - start) + "ms");
        }*/

        for (int i = 1; i <= 45; i++) {
            long start = System.currentTimeMillis();
            System.out.println(i + " : " + tailFeiBo(1,1, i) + " 所花费时间 " + (System.currentTimeMillis() - start) + "ms");
        }




    }

    /**
     * 递归计算
     * @param n
     * @return
     */
    private static int feiBo(int n){
        if(n <= 2){
            return 1;
        }
        return feiBo(n - 1 ) + feiBo(n - 2);
    }

    /**
     * 使用非递归计算
     * @param n
     * @return
     */
    private static int feiBo2(int n){
        if (n <= 2){
            return 1;
        }
        int a = 1;
        int b = 1;
        int c = 0;
        for (int i = 3; i <= n; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }

    /**
     * 使用缓存来减少计算的次数，第一种方法之所以慢，是因为重复的分支也重新计算了
     * @param n
     * @return
     */
    private static int feiBo3(int n){
        if (n <= 2){
            return 1;
        }
        if (data[n]  > 0){
            return data[n];
        }
        int res = feiBo3(n - 1) + feiBo3(n - 2);
        data[n] = res;
        return res;
    }

    private static int tailFeiBo(int pre, int res, int n){
        if(n <= 2){
            return res;
        }
        return tailFeiBo(res,pre + res, n -1);
    }
}
