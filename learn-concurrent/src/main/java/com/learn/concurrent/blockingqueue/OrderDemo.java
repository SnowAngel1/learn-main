package com.learn.concurrent.blockingqueue;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author ChenYP
 * 使用延时队列 模拟订单超时处理
 */

public class OrderDemo {

    private static final DelayQueue<Order> DELAY_QUEUE = new DelayQueue<>();


    public static void main(String[] args) throws InterruptedException {
        String orderId = "12345";

        //     订单延时5s消费
        ZonedDateTime expireTime = ZonedDateTime.now(ZoneId.of("UTC")).plus(5, ChronoUnit.SECONDS);

        Order order = new Order(orderId, expireTime);
        DELAY_QUEUE.put(order);
        System.out.println("订单已创建 ：" + order.getOrderId());

        //阻塞
        Order orderFromQueue = DELAY_QUEUE.take();
        if (orderFromQueue == order){
            System.out.println("订单延时消费：" + order.getOrderId());
        }


    }


    static class Order implements Delayed {
        private String orderId;

        private ZonedDateTime expireTime;

        public Order() {
        }

        public Order(String orderId, ZonedDateTime expireTime) {
            this.orderId = orderId;
            this.expireTime = expireTime;
        }

        public String getOrderId() {
            return orderId;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            long delay = unit.convert(expireTime.toInstant().toEpochMilli() - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
            return delay;
        }

        @Override
        public int compareTo(Delayed o) {
            if (this.getDelay(TimeUnit.MILLISECONDS) > o.getDelay(TimeUnit.MILLISECONDS)) {
                return 1;
            } else if (this.getDelay(TimeUnit.MILLISECONDS) < o.getDelay(TimeUnit.MILLISECONDS)) {
                return -1;
            } else {
                return 0;
            }
        }
    }
}

