package com.yang.springboot.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * netty 服务端，接收终端或者客户端上报的数据
 * <p>
 * <p>
 * NettyServerHandler 被 标注为@Shareable，对于所有的客户端连接来说，都会使用同一个 NettyServerHandler
 *
 * @author yanghao
 * @date 2019/10/31 15:55
 */
//Sharable 标识该handler可以被多个Channel共享
@ChannelHandler.Sharable
@Slf4j
public class NettyServerHandler extends ChannelInboundHandlerAdapter {


    /**
     * 每个传入的消息都要调用
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //读取客户端发来数据
        ByteBuf in = (ByteBuf) msg;
        log.info("这里是服务端，接收客户端发来数据：{}", in.toString(CharsetUtil.UTF_8));

        //向客户端写入数据
        String str = "内容：服务端---->客户端";
        log.info("这里是服务端，开始发送数据给客户端：{}", str);
        ByteBuf out = Unpooled.copiedBuffer(str, CharsetUtil.UTF_8);
//        byte[] bytes = str.getBytes(CharsetUtil.UTF_8);
//
//        //获取二进制抽象 ByteBuf
//        ByteBuf out = ctx.alloc().buffer();
//
//        //填充数据到 ByteBuf
//        out.writeBytes(bytes);

        //开始写数据
        ctx.channel().writeAndFlush(out);
    }


    /**
     * 通知 ChannelInboundHandler最后一次对 channelRead()的调用是当前批量读取中的最后一条消息
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //将未解决的消息丢到远程节点，关闭channel
        log.info("channelReadComplete ==========>");
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
    }

    /**
     * 读取操作期间，异常抛出调用
     *
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
