//package com.yang.springboot.codec;
//
//import io.netty.buffer.ByteBuf;
//import io.netty.channel.ChannelHandlerContext;
//import io.netty.handler.codec.DelimiterBasedFrameDecoder;
//
///**
// * @author Yang Hao
// * @date 2019/11/20 16:12
// */
//
//public class DynamicDelimiterDecoderAdapter extends DelimiterBasedFrameDecoder {
//    public DynamicDelimiterDecoderAdapter(int maxFrameLength, ByteBuf... delimiters) {
//        super(maxFrameLength, delimiters);
//    }
//
//
//    protected ByteBuf decodePackFrame(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
//        return (ByteBuf) decode(ctx, in);
//    }
//}
