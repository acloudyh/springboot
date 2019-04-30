package com.springboot.demo.communication.config;

import com.springboot.demo.communication.jt808.server.TCPServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yanghao
 * @date 2019-04-30 11:19
 */
@Configuration
public class JT808Config {

    @Value("${netty.listen.port}")
    private int port;

    @Bean(initMethod = "startServer", destroyMethod = "stopServer")
    public TCPServer TCPManager() {
        TCPServer tcpServer = new TCPServer(port);
        return tcpServer;
    }
}
