package com.learn.concurrent.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author ChenYP
 */

public class CyclicBarrierDemo1 {



    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {

        CyclicBarrier CYCLIC_BARRIER = new CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {
                System.out.println("发车");
            }
        });


        for (int i = 0; i < 10; i++) {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        CYCLIC_BARRIER.await();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    } catch (BrokenBarrierException e) {
                        throw new RuntimeException(e);
                    }
                }
            },"线程——" + i).start();


        }

    }
}
