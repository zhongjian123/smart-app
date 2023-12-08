package com.smart.smartapp;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description:
 * @author: zhizj
 * @date: 2023/8/7
 */
@SpringBootTest
public class ThreadSafeConcurrentHashMapTests {

    Map<String, QueueInfo> map = new ConcurrentHashMap<>();

    @Test
    public void testConcurrentHashMap() {

        Thread thread1 = new Thread(() -> {
            QueueInfo queueInfo;
            synchronized (map) {
                queueInfo = map.get("key1");
                if (queueInfo == null) {
                    queueInfo = new QueueInfo();
                    map.put("key1", queueInfo);
                }
            }
            for (int i = 0; i < 5; i++) {
                queueInfo.getList().add((long) i);
                queueInfo.increase();
            }
        });

        Thread thread2 = new Thread(() -> {
            QueueInfo queueInfo;
            synchronized (map) {
                queueInfo = map.get("key1");
                if (queueInfo == null) {
                    queueInfo = new QueueInfo();
                    map.put("key1", queueInfo);
                }
            }
            for (int i = 10; i < 15; i++) {
                queueInfo.getList().add((long) i);
                queueInfo.increase();
            }
        });

        Thread thread3 = new Thread(() -> {
            QueueInfo queueInfo = map.get("key3");
            if (queueInfo == null) {
                queueInfo = new QueueInfo();
                map.put("key3", queueInfo);
            }
            for (int i = 10; i < 15; i++) {
                queueInfo.getList().add((long) i);
                queueInfo.increase();
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
            for (String key : map.keySet()) {
                QueueInfo queueInfo = map.get(key);
                System.out.println(key);
                System.out.println(queueInfo.getCount());
                System.out.println(queueInfo.getList());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}


class QueueInfo {
    private int count = 0;
    private List<Long> list = new ArrayList<>();


    public void increase() {
        count = count + 1;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Long> getList() {
        return list;
    }

    public void setList(List<Long> list) {
        this.list = list;
    }
}