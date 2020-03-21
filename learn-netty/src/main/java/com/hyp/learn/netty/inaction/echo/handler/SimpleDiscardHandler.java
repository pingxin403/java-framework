package com.hyp.learn.netty.inaction.echo.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Netty 用一个 WARN-level 日志条目记录未释放的资源,使其能相当简单地找到代码中的违规实
 * 例。然而,由于手工管理资源会很繁琐,您可以通过使用 SimpleChannelInboundHandler 简化
 * 问题。
 * <p>
 * 1.扩展 SimpleChannelInboundHandler
 * 2.不需做特别的释放资源的动作
 * 注意 SimpleChannelInboundHandler 会自动释放资源,而无需存储任何信息的引用。
 *
 * @author hyp
 * Project name is javaframework
 * Include in com.hyp.learn.netty.echo
 * hyp create at 20-3-8
 **/
@ChannelHandler.Sharable
public class SimpleDiscardHandler extends SimpleChannelInboundHandler<Object> {
    //1
    @Override
    public void channelRead0(ChannelHandlerContext ctx,
                             Object msg) {
// No need to do anything special //2
    }
}
