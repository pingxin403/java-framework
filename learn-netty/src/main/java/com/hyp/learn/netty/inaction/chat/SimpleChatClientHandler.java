package com.hyp.learn.netty.inaction.chat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author hyp
 * Project name is javaframework
 * Include in com.hyp.learn.netty.chat
 * hyp create at 20-3-7
 **/
public class SimpleChatClientHandler extends SimpleChannelInboundHandler<String> {
    /**
     * 每当从服务端读到客户端写入信息时,将信息转发给其他客户端的Channel.
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println(msg);
    }

}
