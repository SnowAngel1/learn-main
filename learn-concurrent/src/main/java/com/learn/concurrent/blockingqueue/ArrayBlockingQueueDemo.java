package com.learn.concurrent.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author ChenYP
 * 使用ArrayBlockingQueue 模拟生产者生产消息，消费者消费消息
 */

public class ArrayBlockingQueueDemo {
    private static final int QUEUE_CAPACITY = 5; // 队列大小

    private static final int PRODUCER_DELAY_MS = 1000; // 生产者生产消息间隔

    private static final int CONSUMER_DELAY_MS = 2000; // 消费者消费消息间隔


    public static void main(String[] args) {
        // 创建一个容器为QUEUE_CAPACITY的阻塞队列
        BlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<>(QUEUE_CAPACITY);

        // 创建一个生产者线程
        Runnable producer = ()->{
            while (true){
                // 生产者生产消息，在队列满时阻塞
                try {
                    arrayBlockingQueue.put("producer");
                    System.out.println("生产了一个元素，队列中的元素个数" + arrayBlockingQueue.size());
                    Thread.sleep(PRODUCER_DELAY_MS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        new Thread(producer).start();


        // 创建一个消费者线程
        Runnable consumer = ()->{
            while (true){
                // 生产者生产消息，在队列空时阻塞
                try {
                    String take = arrayBlockingQueue.take();
                    System.out.println("消费了一个元素，队列中的元素个数" + arrayBlockingQueue.size());
                    Thread.sleep(CONSUMER_DELAY_MS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        new Thread(consumer).start();
    }


}
