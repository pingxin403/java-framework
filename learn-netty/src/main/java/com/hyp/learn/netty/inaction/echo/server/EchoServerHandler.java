package com.hyp.learn.netty.inaction.echo.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;


/**
 * 需要继承Netty规定的Handler
 *
 * @author hyp
 */
@ChannelHandler.Sharable                                        //1
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
        System.out.println("EchoServerHandler  channelRegistered");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        System.out.println("EchoServerHandler  channelActive");

    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        System.out.println("EchoServerHandler  channelInactive");
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        super.channelUnregistered(ctx);
        System.out.println("EchoServerHandler  channelUnregistered");
    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
        super.channelWritabilityChanged(ctx);
        System.out.println("EchoServerHandler  channelWritabilityChanged");
    }

    /**
     * @param ctx ChannelHandlerContext上下文对象，包含：管道pipeline、通道
     * @param msg 传送的对象
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx,
                            Object msg) {
        System.out.println("线程名：" + Thread.currentThread().getName());
        ByteBuf in = (ByteBuf) msg;
        System.out.println("Server received: " + in.toString(CharsetUtil.UTF_8));        //2
        ctx.write(in);                            //3
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)//4
                .addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,
                                Throwable cause) {
        cause.printStackTrace();                //5
        ctx.close();                            //6
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        System.out.println("EchoServerHandler handlerAdded");
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        System.out.println("EchoServerHandler handlerRemoved");
    }
}
