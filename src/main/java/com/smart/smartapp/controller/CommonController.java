package com.smart.smartapp.controller;

import com.alibaba.fastjson2.JSONObject;
import com.smart.smartapp.annotation.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @description: 参考：https://juejin.cn/post/7221333917080813623
 * @author: zhizj
 * @date: 2024/7/10
 */
@RestController
@RequestMapping("/common")
public class CommonController {


    @Authentication
    @PostMapping("/alarm/data")
    public String getAlarmData(@RequestBody JSONObject alarmObject) {
        System.out.println("test");
//        int i = 1 / 0;
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
