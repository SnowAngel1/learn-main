package com.learn.concurrent.blockingqueue;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author ChenYP
 * 延时队列DelayQueue的使用
 */

public class DelayQueueDemo {

    private static final DelayQueue<Order> DELAY_QUEUE = new DelayQueue<>();



    public static void main(String[] args) throws InterruptedException {
        DELAY_QUEUE.put(new Order("order_1",System.currentTimeMillis(),5000));
        DELAY_QUEUE.put(new Order("order_2",System.currentTimeMillis(),2000));
        DELAY_QUEUE.put(new Order("order_3",System.currentTimeMillis(),3000));

        while (!DELAY_QUEUE .isEmpty()){
            Order order = DELAY_QUEUE.take();
            System.out.println("处理订单：" + order.getOrderId());
        }



    }

    static class Order implements Delayed {

        private String orderId;

        private long createTime;

        private long delayTime;

        public Order() {
        }

        public Order(String orderId, long createTime, long delayTime) {
            this.orderId = orderId;
            this.createTime = createTime;
            this.delayTime = delayTime;
        }

        public String getOrderId() {
            return orderId;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            long diff = createTime + delayTime - System.currentTimeMillis();
            return unit.convert(diff,TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            long diff = this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS);
            return Long.compare(diff,0);
        }
    }

}
