package com.smart.smartapp;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @description:
 * @author: zhizj
 * @date: 2023/8/31
 */
@SpringBootTest
public class TimerTaskTest {
    private static final Logger LOG = LoggerFactory.getLogger(TimerTaskTest.class);

    @Test
    public void testTask() {
        Timer timer = new Timer();
        // timer.schedule 定时执行任务，如果任务执行时间超过周期，会等待任务执行完毕后，再执行下一次任务
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                LOG.info("------------------------");
                for (int i = 0; i < 2; i++) {
                    LOG.info("开始执行任务: {}", i);
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    LOG.info("结束执行任务:{}", i);
                }
            }
        }, 1000, 3000);

        // 主线程休眠
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
