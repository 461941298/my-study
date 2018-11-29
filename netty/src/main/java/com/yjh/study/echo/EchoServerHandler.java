package com.yjh.study.echo;

import com.sun.deploy.security.ruleset.ExceptionRule;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * @author yjh
 * @discrption
 */
//指明这个handler可以在多个channel之间共享，意味着这个实现必须线程安全
@ChannelHandler.Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    //服务端读取到网络数据后的处理
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        System.out.println("服务端接收到 " + buf.toString(CharsetUtil.UTF_8));
        ctx.write(buf);
    }

    //读取完数据后的处理
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {

        //flush掉所有数据
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
                //flush完成后关闭连接
                .addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
