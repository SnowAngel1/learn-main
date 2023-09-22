package com.springboot.rabbit.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ChenYP
 * 广播模式，交换机类型设置为 fanout
 * 一个生产者，多个消费者收到同样的消息
 */
@Configuration
public class FanoutConfig {

    // 声明队列
    public Queue setFanout(){
        return new Queue("fanout.q1");
    }

    // 声明交换机
    @Bean
    public FanoutExchange setFanoutExchange(){
        return new FanoutExchange("fanoutExchange");
    }

    // 声明Bindying exchange与queue的绑定关系
    @Bean
    public Binding binding(){
        return BindingBuilder.bind(setFanout()).to(setFanoutExchange());
    }
}
