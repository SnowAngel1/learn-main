package com.learn.netty.echo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * @author ChenYP
 * @date 2023/7/24 10:48
 * @Describe 基于Netty的服务器
 */
public class EchoServer {

    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws InterruptedException {

        int port = 9999;
        EchoServer echoServer = new EchoServer(port);
        echoServer.start();

    }


    public void start() throws InterruptedException {
        //定义一个线程组
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            //服务端启动必备
            serverBootstrap.group(eventLoopGroup)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(port))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new EchoServerHandler());
                        }
                    });
            //异步绑定到服务器，sync()会阻塞到完成
            ChannelFuture sync = serverBootstrap.bind().sync();
            //阻塞当前线程，知道服务器ServerChannel被关闭
            sync.channel().closeFuture().sync();
        } finally {
            eventLoopGroup.shutdownGracefully().sync();

        }


    }
}
