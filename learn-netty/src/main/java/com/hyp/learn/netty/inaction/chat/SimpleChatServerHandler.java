package com.hyp.learn.netty.inaction.chat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * 1、SimpleChatServerHandler继承自SimpleChannelInboundHandler，这个类实现了ChannelInboundHandler接口，ChannelInboundHandler提供了很多事件处理的接口方法，我们仅仅需要继承SimpleChannelInboundHandler并重写这些方法。
 * <p>
 * 2、覆盖了父类的handlerAdded(ChannelHandlerContext ctx)事件处理方法，每当从服务端收到新的客户端连接时，客户端的Channel存入ChannelGroup列表中，并通知列表中的其他客户端。在这个方法中我获取到了新连接的channel，并通知所有已经连接到服务器的channel有一个新的客户端连接进来（注意这里的通知不会在服务器端显示），然后把新连接的客户端channel添加到服务端的channelGroup。
 * <p>
 * 3、覆盖了handlerRemoved()事件处理方法。每当从服务端收到客户端断开时,客户端的Channel从ChannelGroup列表中移除，并通知列表中的其他客户端。这个方法的实现和handlerAdded()方法完全相反，它通知所有已经连接到服务器的channel有一个客户端从服务器断开（注意这里的通知不会在服务器端显示），然后把这个客户端channel从服务端的channelGroup中移除。
 * <p>
 * 4、覆盖了 channelRead0() 事件处理方法。每当从服务端读到客户端写入信息时,将信息转发给其他所有的客户端的Channel。
 * <p>
 * 5、覆盖了 channelActive() 事件处理方法。服务端监听到客户端正在活动时调用(在线)。
 * <p>
 * 6、覆盖了 channelInactive() 事件处理方法。服务端监听到客户端不活动是调用(离线).
 * <p>
 * 7、exceptionCaught() 事件处理方法是：当出现 Throwable 对象才会被调用,即当 Netty 由于 IO 错误或者处理器在处理事件时抛出的异常时出现。在大部分情况下,捕获的异常应该被记录下来并且把关联的 channel 给关闭掉。然而这个方法的处理方式会在遇到不同异常的情况下有不同的实现,比如你可能想在关闭连接之前发送一个错误码的响应消息。
 * <p>
 * 所以上面的handler中函数一个比较常规运行顺序是：
 * <p>
 * handlerAdded()
 * channelActive()
 * channelRead0()
 * channelInactive()
 * handlerRemoved()
 *
 * @author hyp
 * Project name is javaframework
 * Include in com.hyp.learn.netty.chat
 * hyp create at 20-3-7
 **/
public class SimpleChatServerHandler extends SimpleChannelInboundHandler<String> {

    public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    /**
     * 每当服务端收到新的客户端连接时,客户端的channel存入ChannelGroup列表中,并通知列表中其他客户端channel
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        //获取连接的channel
        Channel incomming = ctx.channel();
        //通知所有已经连接到服务器的客户端，有一个新的通道加入
        for (Channel channel : channels) {
            channel.writeAndFlush("[SERVER]-" + incomming.remoteAddress() + "加入\n");
        }
        channels.add(ctx.channel());
    }

    /**
     * 每当服务端断开客户端连接时,客户端的channel从ChannelGroup中移除,并通知列表中其他客户端channel
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        //获取连接的channel
        Channel incomming = ctx.channel();
        for (Channel channel : channels) {
            channel.writeAndFlush("[SERVER]-" + incomming.remoteAddress() + "离开\n");
        }
        //从服务端的channelGroup中移除当前离开的客户端
        channels.remove(ctx.channel());
    }

    /**
     * 每当从服务端读到客户端写入信息时,将信息转发给其他客户端的Channel.
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel incomming = ctx.channel();
        //将收到的信息转发给全部的客户端channel
        for (Channel channel : channels) {
            if (channel != incomming) {
                channel.writeAndFlush("[" + incomming.remoteAddress() + "]" + msg + "\n");
            } else {
                channel.writeAndFlush("[You]" + msg + "\n");
            }
        }
    }

    /**
     * 服务端监听到客户端活动
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //服务端接收到客户端上线通知
        Channel incoming = ctx.channel();
        System.out.println("SimpleChatClient:" + incoming.remoteAddress() + "在线");
    }

    /**
     * 服务端监听到客户端不活动
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        //服务端接收到客户端掉线通知
        Channel incoming = ctx.channel();
        System.out.println("SimpleChatClient:" + incoming.remoteAddress() + "掉线");
    }

    /**
     * 当服务端的IO 抛出异常时被调用
     *
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //super.exceptionCaught(ctx, cause);
        Channel incoming = ctx.channel();
        System.out.println("SimpleChatClient:" + incoming.remoteAddress() + "异常");
        //异常出现就关闭连接
        cause.printStackTrace();
        ctx.close();
    }
}