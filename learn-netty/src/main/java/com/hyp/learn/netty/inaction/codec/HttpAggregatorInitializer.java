package com.hyp.learn.netty.inaction.codec;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.*;

/**
 * 1. client: 添加 HttpClientCodec
 * 2. client: 添加 HttpContentDecompressor 用于处理来自服务器的压缩的内容
 * 3. server: HttpServerCodec
 * 4. server: HttpContentCompressor 用于压缩来自 client 支持的 HttpContentCompressor
 * 5. 添加 HttpObjectAggregator 到 ChannelPipeline, 使用最大消息值是 512kb
 *
 * @author hyp
 * Project name is javaframework
 * Include in com.hyp.learn.netty.codec
 * hyp create at 20-3-8
 **/
public class HttpAggregatorInitializer extends ChannelInitializer<Channel> {
    private final boolean isClient;

    public HttpAggregatorInitializer(boolean isClient) {
        this.isClient = isClient;
    }

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        if (isClient) {
            pipeline.addLast("codec", new HttpClientCodec()); //1
            pipeline.addLast("decompressor", new HttpContentDecompressor()); //2
        } else {
            pipeline.addLast("codec", new HttpServerCodec()); //3
            pipeline.addLast("compressor", new HttpContentCompressor()); //4
        }
        pipeline.addLast("aggegator", new HttpObjectAggregator(512 * 1024));


    }
}
