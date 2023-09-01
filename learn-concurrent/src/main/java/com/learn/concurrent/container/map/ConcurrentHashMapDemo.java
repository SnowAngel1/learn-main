package com.learn.concurrent.container.map;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author ChenYP
 * ConcurrentHashMap，统计单词出现的次数
 */

public class ConcurrentHashMapDemo {


    private static ConcurrentHashMap<String, AtomicLong> concurrenthashMap = new ConcurrentHashMap<>();


    private static CountDownLatch countDownLatch = new CountDownLatch(3);

    private static String[] words = new String[]{"we", "is", "it"};

    public static void main(String[] args) {
        Runnable task = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 3; i++) {
                    String word = words[new Random().nextInt(3)];
                    // System.out.println(Thread.currentThread().getName() + "读取到单词：" + word);
                    //     尝试获取全局统计结果
                    AtomicLong number = concurrenthashMap.get(word);
                    if (number == null) {
                        AtomicLong newNumber = new AtomicLong(0);
                        number = concurrenthashMap.putIfAbsent(word, newNumber);
                        if (number == null) {
                            number = newNumber;
                        }
                    }
                    // 在获取到的情况下，统计次数直接加1
                    number.incrementAndGet();
                    System.out.println(Thread.currentThread().getName() + ":" + word + "出现" + number + "次");
                    countDownLatch.countDown();

                }
            }
        };

        new Thread(task, "线程1").start();
        new Thread(task, "线程2").start();
        new Thread(task, "线程3").start();

        try {
            // 计数器为0之前等待
            countDownLatch.await();
            System.out.println(concurrenthashMap.toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }


}
