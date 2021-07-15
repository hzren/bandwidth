/*
 * Copyright (c) 2021
 * User: hzren@outlook.com
 * File: SenderChannelCloseListener.java
 * Date: 2021/07/08 15:23:08
 */

package com.bandwidth.proxy.remote.client;


import com.bandwidth.proxy.base.CloseMsg;
import com.bandwidth.proxy.base.Env;
import com.bandwidth.proxy.base.LogUtils;
import com.bandwidth.proxy.base.MsgUtils;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.socket.SocketChannel;

/**
 * @Date 2021/4/27
 **/

public class SenderChannelCloseListener implements ChannelFutureListener {

    @Override
    public void operationComplete(ChannelFuture future) throws Exception {
        SocketChannel channel = (SocketChannel) future.channel();
        Long id = (Long) channel.attr(Env.KEY_ID).get();
        String target = channel.attr(Env.KEY_TARGET).get();
        MsgUtils.clearPendingMsg(channel);
        LogUtils.err("http channel closed:" + target, future.cause());
        SocketChannel proxy = channel.attr(Env.KEY_SRC).get();
        proxy.writeAndFlush(new CloseMsg(id, channel));
    }
}
