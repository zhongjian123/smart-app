package com.smart.smartapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @description: 引用 https://www.cnblogs.com/guoapeng/p/17020317.html#3-%E4%BD%BF%E7%94%A8java%E5%8E%9F%E7%94%9Fspringboot%E6%B7%B7%E5%90%88
 * @author: zhizj
 * @date: 2023/9/14
 */
@Configuration
@EnableWebSocket
public class WebsocketConfig {
    @Bean
    public ServerEndpointExporter serverEndpoint() {
        return new ServerEndpointExporter();
    }

}
