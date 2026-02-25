package com.smart.smartapp.controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
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
 * @description: 地址 ws://127.0.0.1:8085/myWs
 * | 注解              | 生命周期              | 实例数量   |
 * | --------------- | ----------------- | ------ |
 * | @Controller     | Spring 管理，单例      | 只有一个   |
 * | @ServerEndpoint | WebSocket 容器管理，多例 | 每个连接一个 |
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
     *
     * @param session
     */
    @OnOpen
    public void onOpen(Session session) {
        sessionMap.put(session.getId(), session);
        System.out.println("连接成功");
    }

    /**
     * 连接关闭
     *
     * @param session
     */
    @OnClose
    public void onClose(Session session) {
        sessionMap.remove(session.getId());
        System.out.println("连接关闭");
    }

    /**
     * 接收到消息
     *
     * @param text
     */
    @OnMessage
    public String onMsg(String text) throws IOException {
        return handleOneTalkRequest(text);
//        return "servet 发送：" + text;
    }

    public String handleOneTalkRequest(String text) {
        JSONObject jsonObject = JSON.parseObject(text);
        String type = jsonObject.getString("type");
        switch (type) {
            case "auth-req":
                return "{\"result\":0,\"id\":1,\"type\":\"auth-resp\"}";
            default:
                return "{\"result\":1,\"id\":1,\"type\":\"auth-resp\"}";

        }
    }

    @Override
    public void afterPropertiesSet() {
        executorService.scheduleAtFixedRate(() -> {
            try {
                LOG.info("server execute =============, isEmpty:{}", sessionMap.isEmpty());
                for (String sessionId : sessionMap.keySet()) {
                    Session session = sessionMap.get(sessionId);

                    String content1 = "{\"type\":\"stats-req\",\"id\":1}";
                    session.getBasicRemote().sendText(content1);
                    Thread.sleep(30 * 1000);

//                    String content = sessionId + "哈哈";
                    String content = "{\"type\":\"play-req\",\"id\":1,\"data\":{\"streamurl\":\"mag://192.168.200.111:8082/play/3980376\",\"layer\":0,\"sdp\":\"v=0\\r\\no=MCUServer 140019960635376 2 IN IP4 192.168.200.102\\r\\ns=MCURtpEndPoint\\r\\nc=IN IP4 192.168.200.102\\r\\nt=0 0\\r\\nm=audio 30000 TCP/RTP/AVPF 8 0\\r\\na=rtcp:30001 IN IP4 192.168.200.102\\r\\na=setup:passive\\r\\na=sendrecv\\r\\na=rtpmap:8 PCMA/8000\\r\\na=rtpmap:0 PCMU/8000\\r\\na=ssrc:259080589 cname:a00s7fq0uh2z496v\\r\\nm=video 15060 RTP/AVP 97 96\\r\\na=rtcp:30001 IN IP4 192.168.200.102\\r\\na=setup:passive\\r\\na=sendrecv\\r\\na=rtpmap:97 H264/90000\\r\\na=rtcp-fb:97 nack\\r\\na=rtcp-fb:97 nack pli\\r\\na=rtcp-fb:97 ccm fir\\r\\na=fmtp:97 level-asymmetry-allowed=1;packetization-mode=1;profile-level-id=42e028;max-fs=8192;max-br=3072;max-mbps=486000\\r\\na=rtpmap:96 PS/90000\\r\\na=rtcp-fb:96 nack\\r\\na=rtcp-fb:96 nack pli\\r\\na=rtcp-fb:96 ccm fir\\r\\na=fmtp:96 level-asymmetry-allowed=1;packetization-mode=1\\r\\na=ssrc:259080590 cname:77evu4y9479xs67i\\r\\n\"}}";
                    session.getBasicRemote().sendText(content);
                    Thread.sleep(30 * 1000);

                    String content2 = "{\"type\":\"cancel-play-req\",\"id\":1,\"data\":{\"session_id\":\"s01czfg9:6YLD\"}}";
                    session.getBasicRemote().sendText(content2);

                }
            } catch (Exception e) {
                LOG.error("error:", e);
            }
        }, 30, 100, TimeUnit.SECONDS);
    }
}
