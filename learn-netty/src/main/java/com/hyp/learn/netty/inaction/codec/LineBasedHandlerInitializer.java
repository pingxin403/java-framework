package com.hyp.learn.netty.inaction.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.handler.codec.LineBasedFrameDecoder;

/**
 * 1. 添加一个 LineBasedFrameDecoder 用于提取帧并把数据包转发到下一个管道中的处理
 * 程序,在这种情况下就是 FrameHandler
 * 2. 添加 FrameHandler 用于接收帧
 * 3. 每次调用都需要传递一个单帧的内容
 *
 * @author hyp
 * Project name is javaframework
 * Include in com.hyp.learn.netty.codec
 * hyp create at 20-3-8
 **/
public class LineBasedHandlerInitializer extends ChannelInitializer<Channel> {
    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new LineBasedFrameDecoder(65 * 1024));
        pipeline.addLast(new FrameHandler());
//1
//2
    }

    public static final class FrameHandler extends SimpleChannelInboundHandler<ByteBuf
            > {
        @Override
        public void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
//3
// Do something with the frame
        }
    }
}
