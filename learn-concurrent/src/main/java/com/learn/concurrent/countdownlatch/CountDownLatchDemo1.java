package com.learn.concurrent.countdownlatch;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * @author ChenYP
 * 使用CountDownLatch 模拟百米赛跑，COuntDownLatch必须传入一个int值，当调用await时等待，调用countDown-1 ，当值减为0时开始执行后续逻辑
 */
@Slf4j
public class CountDownLatchDemo1 {

    private static CountDownLatch begin = new CountDownLatch(1); // 裁判
    private static CountDownLatch end = new CountDownLatch(8); // 运动员


    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 8; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        // 预备状态
                        log.info("参赛者" + Thread.currentThread().getName() + "已经准备好了");
                        begin.await(); // 裁判等待
                        // 开始跑步
                        log.info("参赛者" + Thread.currentThread().getName() + "开始跑步");
                        Thread.sleep(new Random().nextInt(5000));
                        // 跑步结束，跑完了，
                        log.info("参赛者" + Thread.currentThread().getName() + "到达终点");
                        // 计数器减 1
                        end.countDown();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }).start();
        }
        //  等待5s 就开始吹哨
        Thread.sleep(5000);
        log.info("开始比赛");
        // 裁判吹哨，计数器减 1
        begin.countDown();
        end.await();
        log.info("比赛结束");
    }


}
