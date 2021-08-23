package com.zhs.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/23 15:50
 * @package: com.zhs.netty.client
 * @description:
 */
public class NettyClient {


    public void start() {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap()
                .group(group)
//该参数的作用就是禁止使用Nagle算法，使用于小数据即时传输
                .option(ChannelOption.TCP_NODELAY, true)
                .channel(NioSocketChannel.class)
                .handler(new NettyClientInitializer());
        try {
            ChannelFuture future = bootstrap.connect("127.0.0.1", 8090).sync();
            System.out.println("客户端成功....");
//发送消息
            future.channel().writeAndFlush("你好啊");
// 等待连接被关闭
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            group.shutdownGracefully();
        }

    }
}