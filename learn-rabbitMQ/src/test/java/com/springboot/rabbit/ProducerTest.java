package com.springboot.rabbit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author ChenYP
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProducerTest {



    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送简单模式消息
     */
    @Test
    public void testHelloWorld(){
        rabbitTemplate.convertAndSend("spring_queue","hello world spring...");
    }



    /**
     * 广播模式
     */
    @Test
    public void testFanout(){
        rabbitTemplate.convertAndSend("spring_fanout_exchange","","hello world spring...");
    }


    /**
     * 路由模式
     */
    @Test
    public void testDirect(){
        rabbitTemplate.convertAndSend("spring_direct_exchange","info","hello world spring...");
    }



    /**
     * topic模式
     */
    @Test
    public void testTopic(){
        rabbitTemplate.convertAndSend("spring_topic_exchange","info","hello world spring...");
    }



}
