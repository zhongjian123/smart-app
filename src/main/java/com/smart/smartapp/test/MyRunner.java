package com.smart.smartapp.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @description: 自定义run方法的2种方式: 在spring boot启动后执行；callRunners(context, applicationArguments)执行的
 * @author: zhizj
 * @date: 2024/12/11
 */
@Component
public class MyRunner implements ApplicationRunner, CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyRunner.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {
        LOGGER.info("自定义run方法1，实现ApplicationRunner接口");
    }

    @Override
    public void run(String... args) throws Exception {
        LOGGER.info("自定义run方法2,实现CommandLineRunner接口");
    }
}
