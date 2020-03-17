package com.hyp.learn.netty.inaction.echo.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
 * 1.扩展 ChannelInboundHandlerAdapter
 * 2.ReferenceCountUtil.release() 来丢弃收到的信息
 * @author hyp
 * Project name is javaframework
 * Include in com.hyp.learn.netty.echo
 * hyp create at 20-3-8
 **/
@ChannelHandler.Sharable
public class DiscardHandler extends ChannelInboundHandlerAdapter {
    //1
    @Override
    public void channelRead(ChannelHandlerContext ctx,
                            Object msg) {
        ReferenceCountUtil.release(msg); //2
    }
}