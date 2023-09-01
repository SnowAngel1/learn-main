package com.learn.concurrent.tools;

import java.util.concurrent.TimeUnit;

/**
 * @author ChenYP
 */

public class SleepTools {

    /**
     * 按秒休眠
     * @param seconds 秒数
     */
    public static final void second(int seconds){
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 按毫秒数休眠
     * @param seconds
     */
    public static final void sleepMs(int seconds){
        try {
            TimeUnit.MICROSECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }


}
