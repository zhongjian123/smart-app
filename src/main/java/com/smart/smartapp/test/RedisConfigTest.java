package com.smart.smartapp.test;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.CompletableFuture;

/**
 * @description:
 * @author: zhizj
 * @date: 2023/11/7
 */
@Component
public class RedisConfigTest implements InitializingBean {

    private RedisTemplate<String, String> redisTemplate;

    public RedisConfigTest(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("++++++++++++++++++++++++");
    }


    @PostConstruct
    public void init() {
        CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(30000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 在这里执行 Redis 操作，例如设置和获取键值对
            redisTemplate.opsForValue().set("testKey", "testValue");
            String value = redisTemplate.opsForValue().get("testKey");
            System.out.println("Retrieved value from Redis: " + value);

        });

    }
}
