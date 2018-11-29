package com.yjh.study.echo;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author yjh
 * @discrption
 */
public class EchoClient {

    private String host;
    private Integer port;

    public EchoClient(String host, Integer port) {
        this.host = host;
        this.port = port;
    }

    public void start() throws InterruptedException {
        //线程组
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            //客户端启动必备
            Bootstrap bootstrap = new Bootstrap();
            bootstrap
                    .group(group)
                    //指明使用nio进行网络通信
                    .channel(NioSocketChannel.class)
                    //远程服务器地址和端口号
                    .remoteAddress(host, port)
                    //ChannelHandler
                    .handler(new EchoClientHandler());
            //连接到远程节点，阻塞直到连接完成
            ChannelFuture future = bootstrap.connect().sync();
            //阻塞，直到channel关闭
            future.channel().closeFuture().sync();

        } finally {
            group.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new EchoClient("localhost", 8888).start();
    }
}
