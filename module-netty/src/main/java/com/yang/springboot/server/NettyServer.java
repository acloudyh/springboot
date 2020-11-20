package com.yang.springboot.server;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

/**
 * 1、创建ServerBootStrap实例
 *
 * 2、设置并绑定Reactor线程池：EventLoopGroup，EventLoop就是处理所有注册到本线程的Selector上面的Channel
 *
 * 3、设置并绑定服务端的channel
 *
 * 5、创建处理网络事件的ChannelPipeline和handler，网络时间以流的形式在其中流转，handler完成多数的功能定制：比如编解码 SSl安全认证
 *
 * 6、绑定并启动监听端口
 *
 * 7、当轮训到准备就绪的channel后，由Reactor线程：NioEventLoop执行pipline中的方法，最终调度并执行channelHandler
 *
 * @author yanghao
 * @date 2019/10/31 18:04
 */
@Slf4j
@Component
public class NettyServer {
    private static final ByteBuf[] DELIMITERS = {Unpooled.wrappedBuffer(new byte[]{0x7e})};
    //bossGroup表示监听端口，accept 新连接的线程组，workerGroup表示处理每一条连接的数据读写的线程组
    private final EventLoopGroup bossGroup = new NioEventLoopGroup();
    private final EventLoopGroup workerGroup = new NioEventLoopGroup();
    private Channel channel;

    public void start(String nettyHost, int nettyPort) {


        try {
            //创建serverBootstrap 实例
            ServerBootstrap serverBootstrap = new ServerBootstrap();

            serverBootstrap
                    .group(bossGroup, workerGroup)
                    //指定NIO传输方式
                    .channel(NioServerSocketChannel.class)
                    //表示系统用于临时存放已完成三次握手的请求的队列的最大长度，如果连接建立频繁，服务器处理创建新连接较慢，可以适当调大这个参数
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    //ChannelOption.SO_KEEPALIVE表示是否开启TCP底层心跳机制，true为开启
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    //使用指定的host 和port
                    .localAddress(new InetSocketAddress(nettyHost, nettyPort))

                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
//                            //常规业务逻辑
                            ch.pipeline().addLast(new NettyServerHandler());

//                            //固定长度的拆包器 FixedLengthFrameDecoder
//                            ch.pipeline().addLast(new FixedLengthFrameDecoder(55));

                            //分隔符拆包器 DelimiterBasedFrameDecoder 可以自定义( 调试工具: 网络调试助手 )
//                            ch.pipeline().addLast(new IdleStateHandler(0, 0, 10, TimeUnit.SECONDS));
//                            ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, DELIMITERS));
//                            ch.pipeline().addLast(new DynamicNettyServerHandler());

//                            // inBound，处理读数据的逻辑链
//                            ch.pipeline().addLast(new TestInboundHandlerA());
//                            ch.pipeline().addLast(new TestInboundHandlerB());
//                            ch.pipeline().addLast(new TestInboundHandlerC());

//                            // outBound，处理写数据的逻辑链
//                            ch.pipeline().addLast(new TestOutboundHandlerA());
//                            ch.pipeline().addLast(new TestOutboundHandlerB());￿
//                            ch.pipeline().addLast(new TestOutboundHandlerC());

//                            // channelHandler 生命周期测试
//                            ch.pipeline().addLast(new TestChannelLifeCycleHandler());
                        }
                    });

//            //异步绑定服务器，调用sync()方法阻塞等待直到绑定完成
//            ChannelFuture channelFuture = serverBootstrap.bind(PORT).sync();

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