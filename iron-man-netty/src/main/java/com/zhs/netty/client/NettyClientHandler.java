package com.zhs.netty.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/23 15:49
 * @package: com.zhs.netty.client
 * @description:
 */
public class NettyClientHandler extends ChannelInboundHandlerAdapter {

    @Override

    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        System.out.println("客户端Active .....");

    }

    @Override

    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        System.out.println("客户端收到消息: {}"+ msg.toString());

    }

    @Override

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        cause.printStackTrace();

        ctx.close();

    }
}
