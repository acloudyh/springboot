package com.yang.springboot.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yanghao
 * @date 2019/10/31 18:04
 */
@Slf4j
public class NettyServer {
    private static final int PORT = 8999;

    public static void main(String[] args) throws InterruptedException {
        //bossGroup表示监听端口，accept 新连接的线程组，workerGroup表示处理每一条连接的数据读写的线程组
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        final ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup)
                //指定NIO传输方式
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new NettyServerHandler());
                    }
                });
        //异步绑定服务器，调用sync()方法阻塞等待直到绑定完成
        log.info("netty 服务启动完毕,port={}", PORT);
        ChannelFuture channelFuture = serverBootstrap.bind(PORT).sync();
//        ChannelFuture channelFuture = serverBootstrap.bind(PORT).addListener(future -> {
//            if (future.isSuccess()) {
//                log.info("绑定端口成功：{}",PORT);
//            } else {
//                log.info("绑定端口成功：{}",PORT);
//            }
//        });

        //获取Channel 的closeFuture，并且阻塞当前线程直到完成
        channelFuture.channel().closeFuture().sync();
        log.info("NettyServer服务启动成功");

    }


}