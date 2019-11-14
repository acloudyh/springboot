package com.yang.springboot.server.test;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Yang Hao
 * @date 2019/11/13 17:43
 */
@Slf4j
public class TestInboundHandlerA extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("TestInboundHandlerA:{}", msg);
        super.channelRead(ctx, msg);
    }
}
