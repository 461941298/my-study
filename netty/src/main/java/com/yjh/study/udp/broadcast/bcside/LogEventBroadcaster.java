package com.yjh.study.udp.broadcast.bcside;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

public class LogEventBroadcaster {

    private final EventLoopGroup group;
    private final Bootstrap bootstrap;

    public LogEventBroadcaster(EventLoopGroup group, Bootstrap bootstrap) {
        this.group = group;
        this.bootstrap = bootstrap;
        //引导该 NioDatagramChannel (无连接的)
        bootstrap.group(group)
                .channel(NioDatagramChannel.class)
                //设置 SO_BROADCAST 套接字选项
                .option(ChannelOption.SO_BROADCAST, true);
    }
}
