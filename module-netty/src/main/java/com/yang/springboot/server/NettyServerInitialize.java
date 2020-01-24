package com.yang.springboot.server;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.*;

/**
 * netty初始化启动
 *
 * @author Yang Hao
 * @date 2020/1/19 15:42
 */
@Component
public class NettyServerInitialize implements ApplicationRunner {
    ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
            .setNameFormat("NettyServer-pool-%d").build();

    //Common Thread Pool
    ExecutorService executor = new ThreadPoolExecutor(5, 200,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

    @Value("${netty.host}")
    String nettyHost;
    @Value("${netty.port}")
    int nettyPort;
    @Resource
    NettyServer nettyServer;

    @Override
    public void run(ApplicationArguments args) {
        executor.execute(() -> {
            nettyServer.start(nettyHost, nettyPort);
        });

    }
}
