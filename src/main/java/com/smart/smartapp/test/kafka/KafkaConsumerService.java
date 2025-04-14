package com.smart.smartapp.test.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: zhizj
 * @date: 2024/8/7
 */
// @Service
public class KafkaConsumerService {

    private final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);

    @KafkaListener(topics = {"analysis-data"}, groupId = "my-group")
    public void consume(String message) {
        System.out.println("Consumed message: " + message);
    }

    // 同一个消费者群中，一个分区的消息只能被一个消费者消费，多余消费者会被闲置
    @KafkaListener(id = "consumerA", topics = "topicA", groupId = "groupA")
    public void consumerA(String message) {
        logger.info("consumerA consumed message: {}", message);
    }

    @KafkaListener(id = "consumerB", topics = "topicA", groupId = "groupA")
    public void consumerB(String message) {
        logger.info("consumerB consumed message: {}", message);
    }

    @KafkaListener(id = "consumerC", topics = "topicA", groupId = "groupA")
    public void consumerC(String message) {
        logger.info("consumerC consumed message: {}", message);
    }

    // 主题一次写入消息，不同消费者群都可全量读取
    @KafkaListener(id = "consumerD", topics = "topicA", groupId = "groupD")
    public void consumerD(String message) {
        logger.info("consumerD consumed message: {}", message);
    }

}

