package com.smart.smartapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @description: 地址 ws://127.0.0.1:8080/myWs
 * @author: zhizj
 * @date: 2023/9/14
 */
@ServerEndpoint("/myWs")
@Component
public class WsServerEndpoint implements InitializingBean {

    private static final Logger LOG = LoggerFactory.getLogger(WsServerEndpoint.class);

    static final Map<String, Session> sessionMap = new HashMap<>();

    ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1, new CustomizableThreadFactory("pool-thread-"));


    /**
     * 连接成功
     * @param session
     */
    @OnOpen
    public void onOpen(Session session) {
        sessionMap.put(session.getId(), session);
        System.out.println("连接成功");
    }

    /**
     * 连接关闭
     * @param session
     */
    @OnClose
    public void onClose(Session session) {
        sessionMap.remove(session.getId());
        System.out.println("连接关闭");
    }

    /**
     * 接收到消息
     * @param text
     */
    @OnMessage
    public String onMsg(String text) throws IOException {
        return "servet 发送：" + text;
    }

    @Override
    public void afterPropertiesSet() {
        executorService.scheduleAtFixedRate(() -> {
            try {
                LOG.info("server execute =============, isEmpty:{}", sessionMap.isEmpty());
                for (String sessionId : sessionMap.keySet()) {
                    Session session = sessionMap.get(sessionId);
                    String content = sessionId + "哈哈";
                    session.getBasicRemote().sendText(content);
                }
            } catch (IOException e) {
                LOG.error("error:", e);
            }
        }, 30, 10, TimeUnit.SECONDS);
    }
}
