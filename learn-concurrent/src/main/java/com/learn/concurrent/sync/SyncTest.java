package com.learn.concurrent.sync;

/**
 * @author ChenYP
 * synchronized关键字的使用
 * 演示没有加锁的情况下，两个线程对同一个int变量累加，在无锁的情况下每次的结果时不对的
 */

public  class  SyncTest {

    private long count = 0;

    private static final Object obj = new Object();


    /**
     * 没有加锁时
     */
    private  void intIncr(){
        count++;
    }

    /**
     * 锁加在同步块上
     */
    private void incrIntSync(){
        synchronized (this){
            count++;
        }
    }


    /**
     * 锁加在方法上
     */
    private synchronized void incrIntSyncMethod(){
        count++;
    }

    /**
     * 锁加在一个对象上
     */
    private void incrIntSyncObj(){
        synchronized (obj){
            count++;
        }
    }




    private static class Count extends Thread{

        private SyncTest syncTest;

        public Count(SyncTest syncTest){
            this.syncTest = syncTest;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                syncTest.incrIntSyncMethod();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SyncTest syncTest = new SyncTest();

        Count count1 = new Count(syncTest);
        Count count2 = new Count(syncTest);

        count1.start();
        count2.start();

        Thread.sleep(50);
        System.out.println(syncTest.count);
    }



}
