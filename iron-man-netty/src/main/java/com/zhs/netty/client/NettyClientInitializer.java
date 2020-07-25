package com.zhs.netty.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/23 15:50
 * @package: com.zhs.netty.client
 * @description:
 */
public class NettyClientInitializer  extends ChannelInitializer<SocketChannel> {

    @Override

    protected void initChannel(SocketChannel socketChannel) throws Exception {

        socketChannel.pipeline().addLast("decoder", new StringDecoder());

        socketChannel.pipeline().addLast("encoder", new StringEncoder());

        socketChannel.pipeline().addLast(new NettyClientHandler());

    }
}
