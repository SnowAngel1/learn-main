package com.learn.kafka;

import com.alibaba.fastjson.JSON;
import com.learn.kafka.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * @author ChenYP
 * @date 2023/7/28 18:35
 * @Describe kafka消息生产者
 */
@Slf4j
public class MsgProducer {

    private final static String TOPIC_NAME = "my-topic";

    public static void main(String[] args) {

        Properties props = new Properties();

        //集群可以配置多个，使用逗号分割(192.168.17.128:9092,192.168.17.128:9093,192.168.17.128:9094)
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"192.168.17.128:9092,192.168.17.128:9093,192.168.17.128:9094");

        //把发送的key从字符串序列化为字节数组
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        //把发送的value从字符串序列化为字节数组
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        Producer<String,String> kafkaProducer =  new KafkaProducer<>(props);

        int msgCount = 5;
        // final CountDownLatch countDownLatch = new CountDownLatch(msgCount);

        for (int i = 0; i < msgCount; i++) {
            Order order = new Order(i,100 + i,1,1000.00);

            ///创建消息载体，指定 topic、分区
            ProducerRecord<String, String> stringStringProducerRecord = new ProducerRecord<>(TOPIC_NAME, 0, order.getOrderId().toString(), JSON.toJSONString(props));
            // 如果未指定分区，具体发送的分区计算公式：hash(key) % partitionNum
            // ProducerRecord<String, String> stringStringProducerRecord = new ProducerRecord<>(TOPIC_NAME,order.getOrderId().toString(), JSON.toJSONString(props));
            //等待消息发送成功地同步阻塞方法
            RecordMetadata recordMetadata = kafkaProducer.send(stringStringProducerRecord).get();
            log.info("同步的方式发送消息结果： topic-{} partition-{} offset-{}",recordMetadata.topic(),recordMetadata.partition(),recordMetadata.offset());

            //异步回调方式发送消息
           /* kafkaProducer.send(stringStringProducerRecord, new Callback() {
                @Override
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    if (e != null){
                        log.error("发送消息失败：{}",e.getStackTrace());
                    }
                    if (recordMetadata != null){
                        log.info("异步的方式发送消息结果： topic-{} partition-{} offset-{}",recordMetadata.topic(),recordMetadata.partition(),recordMetadata.offset());
                    }
                    countDownLatch.countDown();
                }
            });*/
        }
        // countDownLatch.await();
        kafkaProducer.close();
    }
}
