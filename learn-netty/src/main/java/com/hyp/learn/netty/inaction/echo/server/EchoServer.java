package com.hyp.learn.netty.inaction.echo.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

@ChannelHandler.Sharable
public class EchoServer {

    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.err.println(
                    "Usage: " + EchoServer.class.getSimpleName() +
                            " <port>");
            return;
        }
        int port = Integer.parseInt(args[0]);        //1
        new EchoServer(port).start();                //2
    }

    public void start() throws Exception {
        //boss负责连接请求
        //work真正跟客户业务处理
        NioEventLoopGroup boss = new NioEventLoopGroup(); //3
        NioEventLoopGroup work = new NioEventLoopGroup();
        try {
            //服务端启动器，设置启动参数
            ServerBootstrap b = new ServerBootstrap();

            //4
            //链式配置
            //设置两个线程组
            b.group(boss, work)
                    //指定通道类
                    .channel(NioServerSocketChannel.class)        //5
                    //绑定端口
                    .localAddress(new InetSocketAddress(port))    //6

                    //设置boos线程队列得到额连接个数
                    .option(ChannelOption.SO_BACKLOG, 128)
                    //设置work保持活动连接状态
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    //给work线程设置处理器
                    .childHandler(new ChannelInitializer<SocketChannel>() { //7
                        @Override
                        public void initChannel(SocketChannel ch)
                                throws Exception {
                            System.out.println("initChannel ch:" + ch);
                            ch.pipeline()
                                    .addLast(
                                            new EchoServerHandler());
                        }
                    })
            ;

            ChannelFuture f = b.bind().sync();//8
//            f.addListener(new ChannelFutureListener() {
//                @Override
//                public void operationComplete(ChannelFuture future) throws Exception {
//                    if (future.isSuccess()) {
//                        System.out.println("Ok");
//                    }
//                }
//            });
            System.out.println(EchoServer.class.getName() +
                    " started and listen on " + f.channel().localAddress());
            f.channel().closeFuture().sync();            //9
        } finally {
            boss.shutdownGracefully().sync();            //10
            work.shutdownGracefully().sync();
        }
    }
}
