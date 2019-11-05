package com.yang.springboot.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

/**
 * Netty 客户端将会：
 * （1）连接到服务器；
 * （2）发送一个或者多个消息；
 * （3）对于每个消息，等待并接收从服务器发回的相同的消息；
 * （4）关闭连接。
 *
 * @author yanghao
 * @date 2019/11/1 12:00
 */
@Slf4j
public class NettyClient {
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 8999;

    public static void main(String[] args) throws Exception {

        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workerGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new NettyClientHandler());
                    }
                });
        connect(bootstrap, HOST, PORT);

    }

    private static void connect(Bootstrap bootstrap, String host, int port) throws Exception {
        ChannelFuture channelFuture = bootstrap.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {
                log.info("连接成功!");
            }

        });
        channelFuture.channel().closeFuture().sync();
    }
}
