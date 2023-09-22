package com.springboot.rabbit.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ChenYP
 *
 * MQ 配置
 */
@Configuration
public class RabbitMqConfig {
    // 定义交换机名称
    public static final String EXCHANGE_NAME = "boot_topic_exchange";

    // 定义队列名称
    public static final String QUEUE_NAME = "boot_queue";


    // 1、 声明交换机
    @Bean("bootExchange")
    public Exchange bootExchange(){
        return ExchangeBuilder.topicExchange(EXCHANGE_NAME).durable(true).build();
    }


    // 2、声明队列
    @Bean("bootQueue")
    public Queue bootQueue(){
        return QueueBuilder.durable(QUEUE_NAME).build();
    }


    // 3、队列与交换机进行绑定
    @Bean
    public Binding bindQueueExchange(@Qualifier("bootQueue") Queue queue,@Qualifier("bootExchange") Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("boot.#").noargs(); // noargs 表示 没有参数
    }

}
