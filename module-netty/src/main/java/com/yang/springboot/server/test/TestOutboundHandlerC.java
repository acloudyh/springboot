package com.yang.springboot.server.test;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Yang Hao
 * @date 2019/11/13 17:46
 */
@Slf4j
public class TestOutboundHandlerC extends ChannelOutboundHandlerAdapter {
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        log.info("TestOutboundHandlerC:{}", msg);
        super.write(ctx, msg, promise);
    }
}
