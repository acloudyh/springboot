package com.yang.springboot.server;


import com.yang.springboot.codec.DynamicNettyServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

/**
 * @author yanghao
 * @date 2019/10/31 18:04
 */
@Slf4j
@Component
public class NettyServer {
    //bossGroup表示监听端口，accept 新连接的线程组，workerGroup表示处理每一条连接的数据读写的线程组
    private final EventLoopGroup bossGroup = new NioEventLoopGroup();
    private final EventLoopGroup workerGroup = new NioEventLoopGroup();
    private Channel channel;
    private static final ByteBuf[] DELIMITERS = {Unpooled.wrappedBuffer(new byte[]{0x7e})};

    public void start(String nettyHost, int nettyPort) {


        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup);
            //指定NIO传输方式
            serverBootstrap.channel(NioServerSocketChannel.class);
            //使用指定的host 和port
            serverBootstrap.localAddress(new InetSocketAddress(nettyHost, nettyPort));
            serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    //常规业务逻辑
//                            socketChannel.pipeline().addLast(new NettyServerHandler());

                    //固定长度的拆包器 FixedLengthFrameDecoder
//                        socketChannel.pipeline().addLast(new FixedLengthFrameDecoder(55));

                    //分隔符拆包器 DelimiterBasedFrameDecoder 可以自定义( 调试工具: 网络调试助手 )
                    socketChannel.pipeline().addLast(new IdleStateHandler(0, 0, 10, TimeUnit.SECONDS));
                    socketChannel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, DELIMITERS));
                    socketChannel.pipeline().addLast(new DynamicNettyServerHandler());

//                        // inBound，处理读数据的逻辑链
//                        socketChannel.pipeline().addLast(new TestInboundHandlerA());
//                        socketChannel.pipeline().addLast(new TestInboundHandlerB());
//                        socketChannel.pipeline().addLast(new TestInboundHandlerC());
//
//                        // outBound，处理写数据的逻辑链
//                        socketChannel.pipeline().addLast(new TestOutboundHandlerA());
//                        socketChannel.pipeline().addLast(new TestOutboundHandlerB());￿
//                        socketChannel.pipeline().addLast(new TestOutboundHandlerC());

                    // channelHandler 生命周期测试
//                        socketChannel.pipeline().addLast(new TestChannelLifeCycleHandler());
                }
            })
                    //表示系统用于临时存放已完成三次握手的请求的队列的最大长度，如果连接建立频繁，服务器处理创建新连接较慢，可以适当调大这个参数
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    //ChannelOption.SO_KEEPALIVE表示是否开启TCP底层心跳机制，true为开启
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            //异步绑定服务器，调用sync()方法阻塞等待直到绑定完成
//        ChannelFuture channelFuture = serverBootstrap.bind(PORT).sync();

            ChannelFuture channelFuture = serverBootstrap.bind().sync().addListener(future -> {
                if (future.isSuccess()) {
                    log.info("netty 服务启动成功，HOST:[{}], PORT:[{}]", nettyHost, nettyPort);
                } else {
                    log.error("netty 服务启动失败，HOST:[{}], PORT:[{}]", nettyHost, nettyPort);
                }
            });

            //获取Channel 的closeFuture，并且阻塞当前线程直到完成
            channel = channelFuture.channel();
            channel.closeFuture().sync();
        } catch (Exception e) {
            log.error("netty 服务启动失败", e);
            e.printStackTrace();
        } finally {
            // 优雅退出，释放线程池资源
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }


}