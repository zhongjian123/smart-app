package com.smart.smartapp.config;

import com.alibaba.nacos.api.annotation.NacosProperties;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import com.alibaba.nacos.spring.context.annotation.discovery.EnableNacosDiscovery;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @author: zhizj
 * @date: 2024/7/3
 */
@Configuration
@EnableNacosDiscovery(globalProperties = @NacosProperties(serverAddr = "192.168.112:8848"))
@NacosPropertySource(dataId = "nacos-config-example.properties", autoRefreshed = true)
public class NacosConfiguration {
}
