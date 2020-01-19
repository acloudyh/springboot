//package com.yang.springboot.codec;
//
//import io.netty.buffer.ByteBuf;
//import io.netty.buffer.Unpooled;
//import io.netty.channel.ChannelHandlerContext;
//import io.netty.handler.codec.DelimiterBasedFrameDecoder;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//
//import java.nio.charset.Charset;
//
///**
// * 自定义解码器,
// * <p>
// * 0x7e 开头 0x7e 结尾
// * ( 开头 ) 结尾
// * ( = 40=0x28
// *
// * @author Yang Hao
// * @date 2019/11/19 10:42
// */
//@Slf4j
//@Component
//public class DynamicDelimiterBasedFrameDecoder extends DelimiterBasedFrameDecoder {
//
//    private DynamicDelimiterDecoderAdapter packTypeAdapterV1 = new DynamicDelimiterDecoderAdapter(1024, Unpooled.wrappedBuffer(new byte[]{(byte) 0x7e}), Unpooled.wrappedBuffer(new byte[]{(byte) 0x7e}));
//    private DynamicDelimiterDecoderAdapter packTypeAdapterV2 = new DynamicDelimiterDecoderAdapter(1024, Unpooled.wrappedBuffer("(".getBytes()), Unpooled.wrappedBuffer(")".getBytes()));
//
//    public DynamicDelimiterBasedFrameDecoder(int maxFrameLength, ByteBuf... delimiters) {
//        super(maxFrameLength, delimiters);
//    }
//
//    @Override
//    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
//        if (0x7e == in.getByte(0)) {
//            return packTypeAdapterV1.decodePackFrame(ctx, in);
//        }
//        //特殊处理"("")"起始的响应
//        if (0x28 == in.getByte(0)) {
//            ByteBuf byteBuf = packTypeAdapterV2.decodePackFrame(ctx, in);
//            //打印出来具体报文
//            if (null != byteBuf) {
//                String string = byteBuf.toString(Charset.defaultCharset());
//                log.info("分隔符解码器拦截: 0x28 ()报文:[{}]", string);
//            }
//        }
//        return null;
//    }
//}
