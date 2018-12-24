package com.yjh.study.time.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class TimeServer {

    /**
     * @param port 服务运行的端口号
     */
    public void run(int port) throws InterruptedException {
        EventLoopGroup workGroup = new NioEventLoopGroup();
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap b = new ServerBootstrap();

            b.group(bossGroup, workGroup)
                    //指定nio操作
                    .channel(NioServerSocketChannel.class)
                    //添加处理器
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline()
                                    .addLast(new MyTimeEncoder())
                                    .addLast(new TimeServerHandler());
                        }
                    });

            //绑定端口，开始接收进来的连接
            ChannelFuture f = b.bind(port).sync();
            //等待服务器的socket关闭
            f.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }


    }

    public static void main(String[] args) throws InterruptedException {
        new TimeServer().run(8080);
    }
}
