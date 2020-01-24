package com.yang.springboot.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 后期客户端可以用 网络调试助手 模拟报文的发送
 *
 * @author yanghao
 * @date 2019/11/1 12:01
 */
@ChannelHandler.Sharable
@Slf4j
public class NettyClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        for (int i = 0; i < 50; i++) {
            String str = "这是客户端发送的内容哈哈哈哈哈";
            log.info("这里是客户端，开始发送数据给服务端：{}", str);
            ByteBuf buffer = Unpooled.copiedBuffer(str, CharsetUtil.UTF_8);
            ctx.channel().writeAndFlush(buffer);
        }
//        String str = "内容：客户端---->服务端";
//        log.info("这里是客户端，开始发送数据给服务端：{}", str);
//        ByteBuf buffer = Unpooled.copiedBuffer(str, CharsetUtil.UTF_8);
//
////        byte[] bytes = str.getBytes(CharsetUtil.UTF_8);
////
////        //获取二进制抽象 ByteBuf
////        ByteBuf buffer = ctx.alloc().buffer();
////
////        //填充数据到 ByteBuf
////        buffer.writeBytes(bytes);
//
//        //开始写数据
//        ctx.channel().writeAndFlush(buffer);

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        log.info("这里是客户端，读取服务端发送的数据：{}", byteBuf.toString(CharsetUtil.UTF_8));

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
