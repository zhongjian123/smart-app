package com.smart.smartapp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;


@SpringBootApplication
@MapperScan(value = {"com.smart.smartapp"})
@EnableAutoConfiguration(exclude = {FreeMarkerAutoConfiguration.class})
public class SmartAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartAppApplication.class, args);
    }
}
