package com.learn.concurrent.disruptor.consumer;

import com.learn.concurrent.disruptor.event.EventDO;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;

/**
 * @author ChenYP
 * 消息消费者
 * EventHandler：消息会被所有消费者消费
 * WorkHandler：消息只会被一个消费者消费
 */

public class EventDOHandler implements EventHandler<EventDO>, WorkHandler<EventDO> {
    @Override
    public void onEvent(EventDO eventDO, long l, boolean b) throws Exception {
        System.out.println("EventHandler 消费者" + Thread.currentThread().getName() + "获取数据value：" + eventDO.getValue() + "获取name：" + eventDO.getName());
    }

    @Override
    public void onEvent(EventDO eventDO) throws Exception {
        System.out.println("WorkHandler 消费者" + Thread.currentThread().getName() + "获取数据value：" + eventDO.getValue() + "获取name：" + eventDO.getName());
    }
}
