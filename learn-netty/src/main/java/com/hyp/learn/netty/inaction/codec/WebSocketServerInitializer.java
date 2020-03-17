package com.hyp.learn.netty.inaction.codec;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.ContinuationWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;

/**
 * 1. 添加 HttpObjectAggregator 用于提供在握手时聚合 HttpRequest
 * 2. 添加 WebSocketServerProtocolHandler 用于处理色好给你寄握手如果请求是发送
 * 到"/websocket." 端点,当升级完成后,它将会处理Ping, Pong 和 Close 帧
 * 3. TextFrameHandler 将会处理 TextWebSocketFrames
 * 4. BinaryFrameHandler 将会处理 BinaryWebSocketFrames
 * 5. ContinuationFrameHandler 将会处理ContinuationWebSocketFrames
 * 加密 WebSocket 只需插入 SslHandler 到作为 pipline 第一个 ChannelHandler
 *
 * @author hyp
 * Project name is javaframework
 * Include in com.hyp.learn.netty.codec
 * hyp create at 20-3-8
 **/
public class WebSocketServerInitializer extends ChannelInitializer<Channel> {
    @Override
    protected void initChannel(Channel ch) throws Exception {
        ch.pipeline().addLast(
                new HttpServerCodec(),
                new HttpObjectAggregator(65536),
//1
                new WebSocketServerProtocolHandler("/websocket"),
                new TextFrameHandler(),
//2
//3
                new BinaryFrameHandler(),
//4
                new ContinuationFrameHandler());
//5
    }

    public static final class TextFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
        @Override
        public void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
// Handle text frame
        }
    }

    public static final class BinaryFrameHandler extends SimpleChannelInboundHandler<BinaryWebSocketFrame> {
        @Override
        public void channelRead0(ChannelHandlerContext ctx, BinaryWebSocketFrame msg)
                throws Exception {
// Handle binary frame
        }
    }

    public static final class ContinuationFrameHandler extends SimpleChannelInboundHandler<ContinuationWebSocketFrame> {
        @Override
        public void channelRead0(ChannelHandlerContext ctx, ContinuationWebSocketFrame msg) throws Exception {
// Handle continuation frame
        }
    }
}
