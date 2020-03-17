package com.hyp.learn.netty.inaction.chat;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * SimpleChatServerInitializer 用来增加多个的handler处理类到ChannelPipeline上,ChannelPipeline简单理解就可以看成是一个handler容器，包括编码、解码、SimpleChatServerHandler等。
 * @author hyp
 * Project name is javaframework
 * Include in com.hyp.learn.netty.chat
 * hyp create at 20-3-7
 **/
public class SimpleChatServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
        pipeline.addLast("decoder", new StringDecoder());
        pipeline.addLast("encoder", new StringEncoder());
        pipeline.addLast("handler", new SimpleChatServerHandler());

        System.out.println("SimpleChatClient:" + ch.remoteAddress() + "连接上服务器");
    }
}
