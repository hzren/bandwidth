/*
 * Copyright (c) 2021
 * User: hzren@outlook.com
 * File: MpxServerComponent.java
 * Date: 2021/07/08 15:23:08
 */

package com.bandwidth.proxy.remote.multiplex_server;

import com.bandwidth.proxy.base.Config;
import com.bandwidth.proxy.base.Env;
import com.bandwidth.proxy.component.netty.start.ServerStartupListener;
import com.bandwidth.proxy.remote.client.SenderComponent;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;

public class MpxServerComponent {
    public final SenderComponent sender;
    private final EventLoopGroup group = Env.eventLoopGroup("mpx", 1);

    public MpxServerComponent(SenderComponent sender) {
        this.sender = sender;
    }

    public void start() {
        ServerBootstrap b = new ServerBootstrap();
        Env.setOption(b);
        b.group(group, group)
                .channel(Env.SERVER_TYPE)
                .childHandler(new MpxServerChannelInitializer())
                .option(ChannelOption.AUTO_READ, true)
                .childOption(ChannelOption.ALLOW_HALF_CLOSURE, false)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, Boolean.TRUE)
                .childOption(ChannelOption.SO_SNDBUF, 256 * 1024)
                .childOption(ChannelOption.SO_RCVBUF, 128 * 1024)
                .childAttr(Env.KEY_COMPONENT, this)
                .bind(Config.INSTANCE.serverBindIp, Config.INSTANCE.serverBindPort)
                .addListener(ServerStartupListener.DEFAULT);
    }

}
