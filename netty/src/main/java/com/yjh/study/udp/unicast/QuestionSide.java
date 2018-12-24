package com.yjh.study.udp.unicast;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;


public class QuestionSide {

    public static final String QUESTION =  "来一句诗";
    public void run(int port) {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    //这里使用udp，所有使用NioDatagramChannel
                    .channel(NioDatagramChannel.class)
                    .handler(new QuestionHandler());

            //0表示绑定到任意端口号
            Channel channel = bootstrap.bind(0).sync().channel();

            channel.writeAndFlush(new DatagramPacket(
                    //发送的内容
                    Unpooled.copiedBuffer(QUESTION, CharsetUtil.UTF_8),
                    //发送的目的
                    new InetSocketAddress("127.0.0.1", port)
            ));
            if (!channel.closeFuture().await(15000)) {
                System.out.println("查询超时");
            }
        } catch (Exception e) {
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        new QuestionSide().run(8080);
    }
}
