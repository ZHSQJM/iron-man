package com.zhs.netty.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/23 15:45
 * @package: com.zhs.netty.server
 * @description:
 */
public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
       socketChannel.pipeline().addLast("decoder", new StringDecoder(CharsetUtil.UTF_8));
       socketChannel.pipeline().addLast("encoder", new StringEncoder(CharsetUtil.UTF_8));
       socketChannel.pipeline().addLast(new NettyServerHandler());
    }
}
