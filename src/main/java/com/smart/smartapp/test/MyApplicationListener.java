package com.smart.smartapp.test;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * @description: 自定义的监听器
 * @author: zhizj
 * @date: 2024/12/10
 */
public class MyApplicationListener implements ApplicationListener<ApplicationEvent> {
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println("监听器初始化------------------------");
    }
}
