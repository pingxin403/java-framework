package com.hyp.learn.netty.inaction.codec;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpRequestEncoder;
import io.netty.handler.codec.http.HttpResponseDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

/**
 * 1. client: 添加 HttpResponseDecoder 用于处理来自 server 响应
 * 2. client: 添加 HttpRequestEncoder 用于发送请求到 server
 * 3. server: 添加 HttpRequestDecoder 用于接收来自 client 的请求
 * 4. server: 添加 HttpResponseEncoder 用来发送响应给 client
 *
 * @author hyp
 * Project name is javaframework
 * Include in com.hyp.learn.netty.codec
 * hyp create at 20-3-8
 **/
public class HttpPipelineInitializer extends ChannelInitializer<Channel> {
    private final boolean client;

    public HttpPipelineInitializer(boolean client) {
        this.client = client;
    }

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        if (client) {
            pipeline.addLast("decoder", new HttpResponseDecoder());
            pipeline.addLast("encoder", new HttpRequestEncoder());
//1
//2
        } else {
            pipeline.addLast("decoder", new HttpRequestDecoder());
            pipeline.addLast("encoder", new HttpResponseEncoder());
//3
//4
        }
    }
}