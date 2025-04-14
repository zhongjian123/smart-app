package com.smart.smartapp.test;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @description: 自定义的初始化器，在创建springApplication对象时加载所有初始化器实例，并且在准备【上下文环境】时执行这些初始化器
 * 需要在META-INF/spring.factories，配置类地址
 * https://www.cnblogs.com/huigui-mint/p/17517759.html
 * @author: zhizj
 * @date: 2024/11/8
 */
public class MyApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        System.out.println("自定义初始化器 MyApplicationContextInitializer ....................");
    }
}
