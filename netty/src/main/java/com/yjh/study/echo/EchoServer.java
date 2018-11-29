package com.yjh.study.echo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author yjh
 * @discrption
 */
public class EchoServer {

    private Integer port;

    public EchoServer(Integer port) {
        this.port = port;
    }

    public void start() throws InterruptedException {

        final EchoServerHandler echoServerHandler = new EchoServerHandler();
        //线程组
        EventLoopGroup group = new NioEventLoopGroup();

        try {
            //服务端启动必备
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap
                    .group(group)
                    //指明使用nio进行网络通信
                    .channel(NioServerSocketChannel.class)
                    //指明监听的端口号
                    .localAddress(port)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(echoServerHandler);
                        }
                    });
            //绑定到端口，阻塞直到接连完成
            ChannelFuture future = bootstrap.bind().sync();
            //阻塞，直到channel关闭
            future.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new EchoServer(8888).start();
    }
}
