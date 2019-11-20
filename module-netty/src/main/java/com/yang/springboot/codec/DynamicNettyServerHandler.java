package com.yang.springboot.codec;

import com.yang.springboot.util.BitOperator;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
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
public class DynamicNettyServerHandler extends ChannelInboundHandlerAdapter {
    private BitOperator bitOperator = new BitOperator();

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
        ByteBuf byteBuf = (ByteBuf) msg;
        if (byteBuf.readableBytes() <= 0) {
            return;
        }
        //分隔符解码器会将首位标识符去掉
        log.info("DynamicNettyServerHandler: 0x7e 接收 报文[{}]", ByteBufUtil.hexDump(byteBuf));

        //TODO 解码业务逻辑
        //响应客户端,具体响应报文由协议决定 demo中一律回复7e 66 88 7e
        // 66(十六进制)=102(十进制) 88(十六进制)=136(十进制)
        byte[] respByte = bitOperator.concatAll(new byte[]{102}, new byte[]{(byte) 136});

        //增加首位标识符
        ByteBuf respByteBuf = Unpooled.wrappedBuffer(Unpooled.wrappedBuffer(new byte[]{0x7e}), Unpooled.copiedBuffer(respByte), Unpooled.wrappedBuffer(new byte[]{0x7e}));
        log.info("DynamicNettyServerHandler: 0x7e 响应 报文[{}]", ByteBufUtil.hexDump(respByteBuf));
        ctx.channel().writeAndFlush(respByteBuf).sync();
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


    public void main(String[] args) {
        byte[] b = new byte[]{102};
        bitOperator.concatAll(b);
        log.info("");

    }


}
