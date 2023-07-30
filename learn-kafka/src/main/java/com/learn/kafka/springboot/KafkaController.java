package com.learn.kafka.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ChenYP
 * @date 2023/7/30 15:02
 * @Describe 消息生产者
 */
@RestController
public class KafkaController {


    private final static String TOPIC_NAME = "my-topic";


    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    @RequestMapping(value = "/sendKafkaMsg")
    public String sendKafkaMsg(){
        kafkaTemplate.send(TOPIC_NAME,0,"key","this is a msg");
        return "OK";
    }


}
