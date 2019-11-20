package com.yang.springboot.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Yang Hao
 * @date 2019/11/19 17:11
 */
@Slf4j
@ChannelHandler.Sharable
public class TestDecodeHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //读取客户端发来数据
        ByteBuf in = (ByteBuf) msg;
        log.info("TestDecodeHandler 服务端接收：{}", in.toString(CharsetUtil.UTF_8));

    }

    /**
     * 开始连接 处理业务逻辑
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("客户端开始连接￿。。。TODO 业务逻辑");
    }

    /**
     * 断开连接 处理业务逻辑
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("客户端断开连接。。。TODO 业务逻辑");
    }
}
