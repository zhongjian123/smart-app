package com.smart.smartapp.controller;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.api.naming.NamingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: zhizj
 * @date: 2024/7/3
 */
@RestController
@RequestMapping("/nacos")
public class NacosConfigController {

    @NacosValue(value = "${user.name}", autoRefreshed = true)
    private String userName;

    @GetMapping("/config")
    public String getConfig() {
        return userName;
    }
}
