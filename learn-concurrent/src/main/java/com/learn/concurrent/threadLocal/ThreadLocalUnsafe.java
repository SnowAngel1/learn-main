package com.learn.concurrent.threadLocal;

import com.learn.concurrent.tools.SleepTools;

import java.util.Random;

/**
 * @author ChenYP
 * 错误的使用 ThreadLocal，需要在每次使用ThreadLocal的时候，新创建Number对象，否则使用的是同一个Number对象
 * 以下示例中 Number是static的，每次都是同一个对象，去掉static也是可以的，因为在新建线程时，都是新建的，或者使用ThreadLocal.initialValue()
 */

public class ThreadLocalUnsafe implements Runnable{

    public  Number number = new Number(0);


    public static ThreadLocal<Number> value = new ThreadLocal<Number>();/*{
        @Override
        protected Number initialValue() {
            return new Number(0);
        }
    };*/

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(new ThreadLocalUnsafe()).start();
        }

    }


    @Override
    public void run() {
        Random random = new Random();

        // Number number = value.get();

        // 每个线程技术加随机数
        number.setNum(number.getNum() + random.nextInt(100));
        // 将其存储到ThreadLocal中
        value.set(number);
        SleepTools.sleepMs(2);
        // 输出num值
        System.out.println(Thread.currentThread().getName() + "=" + value.get().getNum());
    }


    private static class Number{
        private int num;

        public Number(int num) {
            this.num = num;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }
    }
}
