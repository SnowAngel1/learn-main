package com.learn.concurrent.disruptor.producer;

import com.learn.concurrent.disruptor.event.EventDO;
import com.lmax.disruptor.RingBuffer;

/**
 * @author ChenYP
 * 消息生产者
 */

public class EventProducer {

    private final RingBuffer<EventDO> ringBuffer;

    public EventProducer(RingBuffer<EventDO> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void onData(String value,String name){
        // 获取下一个槽位
        long nextSequence = ringBuffer.next();

        try {
            // 通过槽位获取消息载体
            EventDO eventDO = ringBuffer.get(nextSequence);
            // 写入消息数据
            eventDO.setName(name);
            eventDO.setValue(value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("生产者"+ Thread.currentThread().getName() + "发送数据value：" + value + "name：" + name);
            // 发布事件
            ringBuffer.publish(nextSequence);
        }


    }
}
