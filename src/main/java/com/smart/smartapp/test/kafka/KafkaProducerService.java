package com.smart.smartapp.test.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: zhizj
 * @date: 2024/8/7
 */
@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String topic, String message) {
        kafkaTemplate.send(topic, message);
    }

    public void sendMessage(String topic, Integer partition, String key, String message) {
        kafkaTemplate.send(topic, partition, key, message);
    }

}
