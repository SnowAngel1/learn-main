package com.springboot.rabbit.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ChenYP
 */
@Configuration
public class TopicConfig {



    @Bean
    public Queue setQueue(){
        return new Queue("topicQueue");
    }


    @Bean
    public TopicExchange setTopicExchange(){
        return new TopicExchange("topic_exchange");
    }


    @Bean
    public Binding binding(){
        return BindingBuilder.bind(setQueue()).to(setTopicExchange()).with("changsha.*");
    }


    @Bean
    public Binding bindingRoutingKey(){
        return BindingBuilder.bind(setQueue()).to(setTopicExchange()).with("#.beijing");
    }



}
