package com.springboot.rabbit.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ChenYP
 */
@Configuration
public class WorkConfig {

  @Bean
  public Queue workQueue(){
      return new Queue("work_queue");
  }


}
