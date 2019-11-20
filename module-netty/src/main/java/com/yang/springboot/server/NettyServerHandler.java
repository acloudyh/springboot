package com.yang.springboot.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
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
        log.info("NettyServerHandler 服务端接收数据：{}", in.toString(CharsetUtil.UTF_8));

        //向客户端写入数据
        String str = "内容：NettyServerHandler服务端---->客户端";
        ByteBuf out = Unpooled.copiedBuffer(str, CharsetUtil.UTF_8);

        //开始写数据
        ctx.channel().writeAndFlush(out);
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

    public static void main(String[] args) {
        String s = "0200006a064762924976014d000003500004100201d9f1230743425e000300a6ffff190403133450000000250400070008000000e2403836373733323033383535333838392d627566322d323031392d30342d30332d31332d33342d34392d3735372d70686f6e652d2e6a706700000020000c14cde78d";
        log.info("转换后的字符串---{}", getFileAddSpace(s));
    }

    public static String getFileAddSpace(String replace) {
        String regex = "(.{2})";
        replace = replace.replaceAll(regex, "$1 ");
        return replace;
    }
}
