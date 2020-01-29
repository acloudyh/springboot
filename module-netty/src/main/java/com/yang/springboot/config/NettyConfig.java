//package com.yang.springboot.config;
//
//import com.yang.springboot.server.NettyServer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * 也可以使用这个启动,已改为NettyServerInitialize初始化中启动 netty
// *
// * @author Yang Hao
// * @date 2020/1/19 15:42
// */
//@Configuration
//public class NettyConfig {
//
//
//    @Bean
//    public NettyServer NettyStartServer() {
//        NettyServer nettyServer = new NettyServer((byte) 0x7e);
//        nettyServer.start();
//        return nettyServer;
//    }
//
//
//}
