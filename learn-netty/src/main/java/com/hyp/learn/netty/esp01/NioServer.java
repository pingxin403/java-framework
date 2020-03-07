package com.hyp.learn.netty.esp01;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author hyp
 * Project name is javaframework
 * Include in com.hyp.learn.netty.esp01
 * hyp create at 20-3-6
 **/
public class NioServer {
    public static void main(String[] args) throws IOException {
        //1. socketChannel
        ServerSocketChannel socketChannel = ServerSocketChannel.open();

        //2. Selector
        Selector selector = Selector.open();

        //3. 绑定本地端口
        socketChannel.socket().bind(new InetSocketAddress(6666));

        //4. 配置非阻塞
        socketChannel.configureBlocking(false);

        //5.注册selector到ServerSocketChannel，定义连接事件
        socketChannel.register(selector, SelectionKey.OP_ACCEPT);

        //循环等待客户端
        while (true) {
            //等待1秒
            if (selector.select(1000) == 0) {
                System.out.println("服务器等待1秒，无连接");
                continue;
            }

            //取到关注该相关事件的key
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = keys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                if (key.isAcceptable()) {
                    //给该客户端生成channel
                    //不会阻塞
                    SocketChannel channel = socketChannel.accept();
                    channel.configureBlocking(false);
                    //给客户端channel关联到selector
                    channel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                    System.out.println("新连接接入");
                } else if (key.isReadable()) {
                    //通过key获取channel
                    SocketChannel channel = (SocketChannel) key.channel();

                    //获取到channel关联的buffer
                    ByteBuffer buffer = (ByteBuffer) key.attachment();

                    int n = 0;
                    while ((n = channel.read(buffer)) > 0) {
                        System.out.println("客户端发送数据:" + new String(buffer.array()));
                    }
                }
                iterator.remove();
            }

        }

    }
}
