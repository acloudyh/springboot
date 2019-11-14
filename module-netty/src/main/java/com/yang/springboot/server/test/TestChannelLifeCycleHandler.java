package com.yang.springboot.server.test;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 测试 channelHandler 生命周期;可根据业务实现业务逻辑
 * <p>
 * 当客户端连接时，对于服务端来说，channel开启
 *      handlerAdded() -> channelRegistered() -> channelActive() -> channelRead() -> channelReadComplete()
 *
 * 当客户端关闭时 ，对于服务端来说，channel被关闭
 *      channelInactive() -> channelUnregistered() -> handlerRemoved()
 * @author Yang Hao
 * @date 2019/11/14 11:34
 */
@Slf4j
public class TestChannelLifeCycleHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        log.info("添加逻辑处理器：handlerAdded()。。。。");
        super.handlerAdded(ctx);
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        log.info("Channel 注册到 EventLoop 并且能够处理 I/O 时被调用：channelRegistered()。。。。");
        super.channelRegistered(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("channel 准备就绪：channelActive()。。。。");
        super.channelActive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("channel 读取数据时被调用：channelRead()。。。。");

        //接收客户端传来的数据，解码，自己的业务逻辑
        ByteBuf in = (ByteBuf) msg;
        log.info("服务端接收客户端发来数据：{}", in.toString(CharsetUtil.UTF_8));

        //处理之后再向客户端写数据，给予客户端响应
        String str = "内容：服务端---->客户端";
        ByteBuf out = Unpooled.copiedBuffer(str, CharsetUtil.UTF_8);
        ctx.channel().writeAndFlush(out);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        log.info("channel 读完一次：channelReadComplete()。。。。");
        super.channelReadComplete(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("channel 被关闭：channelInactive()。。。。");
        super.channelInactive(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        log.info("channel 从EventLoop注销: channelUnregistered()。。。。");
        super.channelUnregistered(ctx);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        log.info("移除逻辑处理器：handlerRemoved()。。。。");
        super.handlerRemoved(ctx);
    }
}
