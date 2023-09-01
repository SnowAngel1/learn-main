package com.learn.concurrent.disruptor;

import com.learn.concurrent.disruptor.consumer.EventDOHandler;
import com.learn.concurrent.disruptor.event.EventDO;
import com.learn.concurrent.disruptor.event.EventDOFactory;
import com.learn.concurrent.disruptor.producer.EventProducer;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.Executors;

/**
 * @author ChenYP
 * 最简单的Disruptor实现：多生产者
 *      1、创建Disruptor
 *      2、绑定消费者
 *      3、启动Disriptor
 *      4、创建容器，创建生产者，发送消息
 *
 */

public class DisruptorDemo3 {

    public static void main(String[] args) {
        // 创建 Disruptor
        Disruptor<EventDO> disruptor = new Disruptor<>(
                new EventDOFactory(),
                1024*1024,
                Executors.defaultThreadFactory(),
                ProducerType.SINGLE, // 单生产者
                new YieldingWaitStrategy() // 等待策略
        );
        // 设置消费者用于处理Ring Buffer的事件
        // disruptor.handleEventsWith(new EventDOHandler());
        // 设置多个消费者，消息会被重复消费
        // disruptor.handleEventsWith(new EventDOHandler(),new EventDOHandler());
        // 设置多消费者。消费者要实现WorkHandler接口，一条消息只会被一个消费者消费
        // disruptor.handleEventsWithWorkerPool(new EventDOHandler(),new EventDOHandler());
        // 设置按照消费者优先级消费   消费者A ->(消费者B 消费者C) ——>消费者D
        disruptor.handleEventsWith(new EventDOHandler())
                .thenHandleEventsWithWorkerPool(new EventDOHandler(),new EventDOHandler())
                .then(new EventDOHandler());


        // 启动Disruptor
        disruptor.start();
        // 创建ringBuffer容器
        RingBuffer<EventDO> ringBuffer = disruptor.getRingBuffer();
        // 创建生产者
        EventProducer eventProducer = new EventProducer(ringBuffer);
        // 发送消息
        for (int i = 0; i < 100; i++) {
            eventProducer.onData(""+ i,"Chen" + i);
        }
        disruptor.shutdown();
    }
}
