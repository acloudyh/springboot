package com.springboot.demo.communication.jt808.service.codec;


import com.springboot.demo.util.HexStringUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 该解码器只是为了自己日志所用,没其他作用
 *
 * @author yanghao
 * @date 2019-04-29 11:16
 */
@Slf4j
public class Decoder4LoggingOnly extends ByteToMessageDecoder {


    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        String hex = buf2Str(in);
        log.info("ip={},hex = {}", ctx.channel().remoteAddress(), hex);

        ByteBuf buf = Unpooled.buffer();
        while (in.isReadable()) {
            buf.writeByte(in.readByte());
        }
        out.add(buf);
    }

    private String buf2Str(ByteBuf in) {
        byte[] dst = new byte[in.readableBytes()];
        in.getBytes(0, dst);
        return HexStringUtil.toHexString(dst);
    }
}
