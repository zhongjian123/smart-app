package com.smart.smartapp.controller;

import com.alibaba.fastjson2.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @description:
 * @author: zhizj
 * @date: 2024/7/10
 */
@RestController
@RequestMapping("/common")
public class CommonController {


    @PostMapping("/alarm/data")
    public String getAlarmData(@RequestBody JSONObject alarmObject) {
        System.out.println("test");
        return "success" + Math.random();
    }

    @PostMapping("/alarm/video")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") List<MultipartFile> files) {
        for (MultipartFile file : files) {
            String originalFilename = file.getOriginalFilename();
            // 处理每个文件
            System.out.println("Received file: " + originalFilename);
        }
        return ResponseEntity.ok("Files uploaded successfully");
    }


}
