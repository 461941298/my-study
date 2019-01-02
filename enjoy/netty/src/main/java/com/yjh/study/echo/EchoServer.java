package com.yjh.study.echo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class EchoServer {

    private final Integer port;

    public EchoServer(Integer port) {
        this.port = port;
    }

    private void start() throws InterruptedException {
        //创建 EventLoopGroup
        EventLoopGroup group = new NioEventLoopGroup();

        try {
            //服务端引导
            ServerBootstrap bootstrap = new ServerBootstrap();

            bootstrap.group(group)
                    //指定使用nio传输
                    .channel(NioServerSocketChannel.class)
                    //设置端口
                    .localAddress(this.port)
                    //添加 EchoServerHandler 到 Channel 的 ChannelPipeline
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new EchoServerHandler());
                        }
                    });

            //绑定的服务器;sync 等待服务器关闭
            ChannelFuture channelFuture = bootstrap.bind().sync();
            System.out.println(EchoServer.class.getName() + " Started and listen on " +
                    channelFuture.channel().localAddress());

            //关闭 channel 和 块，直到它被关闭
            channelFuture.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new EchoServer(9090).start();
    }
}
