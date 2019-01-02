package com.yjh.study.time.server;

import com.yjh.study.time.MyTime;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.util.CharsetUtil;

public class MyTimeEncoder extends MessageToByteEncoder<MyTime> {
    @Override
    protected void encode(ChannelHandlerContext ctx, MyTime msg, ByteBuf out) throws Exception {
        out.writeBytes(Unpooled.copiedBuffer(msg.getSignature(), CharsetUtil.UTF_8));
        out.writeLong(msg.getValue());
    }
}
