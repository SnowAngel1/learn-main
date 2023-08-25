package com.learn.concurrent.sync;

/**
 * @author ChenYP
 *  错位的加锁
 */

public class TestSyncInteger {

    public static void main(String[] args) {
        Work work = new Work(1);

        for (int i = 0; i < 5; i++) {
            new Thread(work).start();
        }

    }





    private static class Work implements Runnable{
        private Integer i;

        public Work(Integer i) {
            this.i = i;
        }

        @Override
        public void run() {
        synchronized (i){
            Thread thread = Thread.currentThread();
            System.out.println(thread.getName() + "hahah" + i +  "----@" + System.identityHashCode(i));
            i++;
            System.out.println(thread.getName()  + "hahah" + i +  "----&&&" + System.identityHashCode(i));
        }



        }
    }
}
