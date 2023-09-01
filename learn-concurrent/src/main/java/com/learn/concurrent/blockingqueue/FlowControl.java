package com.learn.concurrent.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author ChenYP
 * 使用ArrayBlockingQueue实现限流
 */

public class FlowControl {

    private static final int MAX_REQUEST = 100;

    private static final int MAX_TIMEOUT = 1000;

    private static final ArrayBlockingQueue ARRAY_BLOCKING_QUEUE = new ArrayBlockingQueue(MAX_REQUEST);


    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 处理请求
                handlerRequest();
            }
        }).start();

        // 模拟 200 次请求
        for (int i = 0; i < 200; i++) {
            // 队列已满，等待一段时间在重新尝试
            if(ARRAY_BLOCKING_QUEUE.size() > MAX_REQUEST){
                TimeUnit.MILLISECONDS.sleep(MAX_TIMEOUT);
            }
        //     将请求放到队列中
            ARRAY_BLOCKING_QUEUE.offer(new Object());
        }





    }

    private static void handlerRequest() {

        while (true){
        //     获取队列中的请求
            Object request = ARRAY_BLOCKING_QUEUE.poll();
            System.out.println("处理请求：" + request);
            if (request != null){
                try {
                    // 模拟处理请求时间
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }


        }

    }

}
