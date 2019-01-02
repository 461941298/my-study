package com.yjh.study.time.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.CharsetUtil;

import java.util.List;

public class MyTimeDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        String signature = in.readBytes(7).toString(CharsetUtil.UTF_8);
        long currentTime = in.readLong();
        System.out.println(signature);
        System.out.println(currentTime);
    }
}
