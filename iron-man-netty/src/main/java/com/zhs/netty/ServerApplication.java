package com.zhs.netty;

import com.zhs.netty.client.NettyClient;
import com.zhs.netty.server.NettyServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.InetSocketAddress;

/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/23 15:48
 * @package: com.zhs.netty
 * @description:
 */
@SpringBootApplication
public class ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
//启动服务端
        NettyServer nettyServer = new NettyServer();
        nettyServer.start(new InetSocketAddress("127.0.0.1", 8090));
        //启动客户端
        NettyClient nettyClient = new NettyClient();
        nettyClient.start();
    }
}
