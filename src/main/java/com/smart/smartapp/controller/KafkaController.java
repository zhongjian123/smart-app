package com.smart.smartapp.controller;

import com.alibaba.fastjson2.JSONObject;
import com.smart.smartapp.test.kafka.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @description:
 * @author: zhizj
 * @date: 2024/12/6
 */
@RestController
@RequestMapping("/kafka")
public class KafkaController {

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @PostMapping
    public ResponseEntity<Object> sendKafkaMessage(@RequestBody JSONObject jsonObject) {
        if (jsonObject.getInteger("partition") == null) {
            kafkaProducerService.sendMessage(jsonObject.getString("topic"), jsonObject.getString("message"));
        } else {
            kafkaProducerService.sendMessage(jsonObject.getString("topic"), jsonObject.getInteger("partition"), jsonObject.getString("key"), jsonObject.getString("message"));
        }
        return ResponseEntity.ok("successfully");
    }

}
