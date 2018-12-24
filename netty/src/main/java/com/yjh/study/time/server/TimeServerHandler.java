package com.yjh.study.time.server;

import com.yjh.study.time.MyTime;
import io.netty.channel.*;

@ChannelHandler.Sharable
public class TimeServerHandler extends ChannelInboundHandlerAdapter {

//    @Override
//    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        //开辟一个8字节的buffer来存储时间信息
//        ByteBuf buffer = ctx.alloc().buffer(8);
//
//        //将当前时间写入到buffer中
//        buffer.writeLong(System.currentTimeMillis());
//
//        //将数据写入channel并刷新，（netty中都是异步操作，并不会立即执行，返回一个channelFuture可以通过回调关闭）
//        ChannelFuture channelFuture = ctx.writeAndFlush(buffer);
//
//        //写请求完成后回调关闭方法
//        channelFuture.addListener(ChannelFutureListener.CLOSE);
//    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        MyTime myTime = new MyTime();
        myTime.setValue(System.currentTimeMillis());
        System.out.println(myTime.getValue());
        ChannelFuture channelFuture = ctx.writeAndFlush(myTime);
        //写请求完成后回调关闭方法
        channelFuture.addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
