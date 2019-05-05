package com.springboot.demo.communication.jt808.service;

import com.springboot.demo.communication.jt808.server.SessionManager;
import com.springboot.demo.communication.jt808.vo.Session;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yanghao
 * @date 2019-04-29 11:22
 */
@Slf4j
public class BaseMsgProcessService {

    protected SessionManager sessionManager;

    public BaseMsgProcessService() {
        this.sessionManager = SessionManager.getInstance();
    }

    protected ByteBuf getByteBuf(byte[] arr) {
        ByteBuf byteBuf = PooledByteBufAllocator.DEFAULT.directBuffer(arr.length);
        byteBuf.writeBytes(arr);
        return byteBuf;
    }

    public void send2Client(Channel channel, byte[] arr) throws InterruptedException {
        ChannelFuture future = channel.writeAndFlush(Unpooled.copiedBuffer(arr)).sync();
        if (!future.isSuccess()) {
            log.error("发送数据出错:{}", future.cause());
        }
    }

    protected int getFlowId(Channel channel, int defaultValue) {
        Session session = this.sessionManager.findBySessionId(Session.buildId(channel));
        if (session == null) {
            return defaultValue;
        }

        return session.currentFlowId();
    }

    protected int getFlowId(Channel channel) {
        return this.getFlowId(channel, 0);
    }

}
