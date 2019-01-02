package com.yjh.study.udp.unicast;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;

public class AnswerHandler extends SimpleChannelInboundHandler<DatagramPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket msg) throws Exception {
        //获得请求
        String req = msg.content().toString(CharsetUtil.UTF_8);
        System.out.println(req);
        if (QuestionSide.QUESTION.equals(req)) {

            ctx.writeAndFlush(
                    new DatagramPacket(
                            //发送的内容
                            Unpooled.copiedBuffer(AnswerSide.ANSER + "呵呵", CharsetUtil.UTF_8),
                            //获得发送的目的
                            msg.sender()
                    )
            );

        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
