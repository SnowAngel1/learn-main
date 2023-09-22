package com.springboot.rabbit.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ChenYP
 * 路由模式 routingkey
 */
@Configuration
public class DirectConfig {



    @Bean
    public Queue setQueue(){
        return new Queue("directQueue");
    }


    @Bean
    public DirectExchange setDirectExchange(){
        return new DirectExchange("direct_exchange");
    }


    @Bean
    public Binding binding(){
        return BindingBuilder.bind(setQueue()).to(setDirectExchange()).with("changsha");
    }


    @Bean
    public Binding bindingRoutingKey(){
        return BindingBuilder.bind(setQueue()).to(setDirectExchange()).with("beijing");
    }

}
