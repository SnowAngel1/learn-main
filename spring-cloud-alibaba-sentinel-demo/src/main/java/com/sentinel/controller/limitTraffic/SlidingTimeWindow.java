package com.sentinel.controller.limitTraffic;

import java.util.LinkedList;
import java.util.Random;

/**
 * @author：江南
 * @Description 滑动窗口限流实现
 * 假设某个服务最多只能每秒处理100个请求，我们可以设置一个1秒钟的滑动时间窗口
 * 窗口中有10个格子，每个格子100毫秒，每100毫秒移动一次，每次移动都需要记录当前服务请求的次数
 * @since 1.0.0
 */
public class SlidingTimeWindow {
    /**
     * 服务访问次数，可以放在redis中，实现分布式系统的访问计数
     */
    Long counter = 0L;

    /**
     * 使用LinkedList来记录滑动窗口的10个格子
     */
    LinkedList<Long> slots = new LinkedList<>();

    public static void main(String[] args) throws InterruptedException {

        SlidingTimeWindow timeWindow = new SlidingTimeWindow();
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    timeWindow.doCheck();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        }).start();

        while (true){
            timeWindow.counter++;
            Thread.sleep(new Random().nextInt(15));
        }
    }

    private void doCheck() throws InterruptedException {
        while (true){
            Thread.sleep(100);
            slots.addLast(counter);
            if (slots.size() > 10){
                slots.removeFirst();
            }
            if ((slots.peekLast() - slots.peekFirst()) > 100){
                System.out.println("限流了。。。");
            }else {
                System.out.println("通过");
            }
        }
    }


}
