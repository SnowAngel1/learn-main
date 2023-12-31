package com.learn.concurrent.cyclicbarrier;

import java.util.concurrent.*;

/**
 * @author ChenYP
 * CyclicBarrier 循环栅栏，实现发车
 */

public class CyclicBarrierDemo {

    private static ExecutorService executorService = Executors.newFixedThreadPool(5);

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, () -> {
            System.out.println("人齐了，准备发车");
        });
        for (int i = 0; i < 15; i++) {
            final int id = i + 1;
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(id + "号马上就到");
                        int sleepMills = ThreadLocalRandom.current().nextInt(2000);
                        Thread.sleep(sleepMills);
                        System.out.println(id + "号到了，上车");
                        cyclicBarrier.await();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    } catch (BrokenBarrierException e) {
                        throw new RuntimeException(e);
                    }
                }
            });


        }


    }


}
