package com.yang.springboot.client;

import com.google.common.base.Charsets;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

        List<String> sendDatas= Arrays.asList(
                "7E02000043770191202229001000000000040010020318343c006d8ba000010005000021012908202430011f310105d40164d5020050da03000115db02002fdc0400000000fd0901060200007f780212f77E");

        sendDatas = Arrays.asList(
                "7e 02 00 00 43 77 01 91 20 22 29 00 09 00 00 00 00 02 00 00 40 03 17 ee dc 00 6d b0 62 00 00 00 00 00 00 21 01 28 14 33 33 30 01 1f 31 01 00 d4 01 64 d5 02 00 a0 da 03 00 01 0c db 02 00 2e dc 04 00 00 00 00 fd 09 01 06 02 00 00 7f 78 02 12 86 7e"
        );

        sendDatas.forEach(x -> {
            log.info("这里是客户端，开始发送数据给服务端：{}", x);

            ByteBuf buffer = Unpooled.copiedBuffer(x, Charset.forName("GBK"));
            ctx.channel().writeAndFlush(buffer);
        });
//        for (int i = 0; i < 50; i++) {
//            String str = "这是客户端发送的内容哈哈哈哈哈";
//            log.info("这里是客户端，开始发送数据给服务端：{}", str);
//            ByteBuf buffer = Unpooled.copiedBuffer(str, CharsetUtil.UTF_8);
//            ctx.channel().writeAndFlush(buffer);
//        }
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
