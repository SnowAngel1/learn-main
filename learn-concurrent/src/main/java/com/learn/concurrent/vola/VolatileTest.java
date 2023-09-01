package com.learn.concurrent.vola;

import com.learn.concurrent.tools.SleepTools;

/**
 * @author ChenYP
 * Volatile关键字，最轻量的通信/同步机制，如果不使用volatile关键字该程序是不会停止的
 */

public class VolatileTest {

    private static    boolean flag = true;

    private static int number = 0;



    private static class PrintThread extends Thread{

        @Override
        public void run() {
            System.out.println("PrintThread is running...");
            while (flag){
                // 加上打印也可以实现可见性，因为打印里边使用了synchronized关键字
                System.out.println("1111");
            }

            System.out.println("Number= " + number);
        }
    }

    public static void main(String[] args) {
        new PrintThread().start();
        // 此休眠不可去掉，因为可能开启的线程还没来得及执行就改了状态
        SleepTools.second(1);

        number = 51;
        flag = false;
        System.out.println("main end");


    }


}
