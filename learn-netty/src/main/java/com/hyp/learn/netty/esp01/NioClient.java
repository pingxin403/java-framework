package com.hyp.learn.netty.esp01;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author hyp
 * Project name is javaframework
 * Include in com.hyp.learn.netty.esp01
 * hyp create at 20-3-6
 **/
public class NioClient {
    public static void main(String[] args) throws IOException {
        SocketChannel channel = SocketChannel.open();

        channel.configureBlocking(false);

        InetSocketAddress socketAddress = new InetSocketAddress("127.0.0.1", 6666);
        if (!channel.connect(socketAddress)) {
            while (!channel.finishConnect()) {
                System.out.println("do something");
            }
        }

        String s = "Hello World";
        if (channel.isConnected()) {
            channel.write(ByteBuffer.wrap(s.getBytes()));
        }

        System.in.read();
    }
}
