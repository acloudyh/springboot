package com.yang.springboot.server;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Netty服务器初始化
 *
 * @author yupl@acloudchina.com
 * @date 2020-01-07 11:39 上午
 * @since V2.0.0
 */
@Component
public class NettyServerInitialize implements ApplicationRunner {

    @Resource
    NettyServer nettyServer;

    @Override
    public void run(ApplicationArguments args) {
        nettyServer.start();
    }
}
