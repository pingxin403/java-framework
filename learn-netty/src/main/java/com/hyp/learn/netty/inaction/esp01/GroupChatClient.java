package com.hyp.learn.netty.inaction.esp01;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Scanner;
import java.util.Set;

/**
 * 定义一个 Charset 字符集用于解析数据
 * 定义两个 ByteBuffered 缓冲区用于收发数据
 * 定义一个 Selector 用于监听通道事件
 * 通过 SocketChannel 的 open() 打开一个 SocketChannel 实例
 * 设置 SocketChannel 实例为非阻塞模式，并调用 connect() 连接到服务端
 * 注册 SocketChannel 的连接就绪事件到 Selector
 * 循环监听事件
 * 调用 Selector 的 select 方法阻塞直到有准备就绪的通道
 * 调用 Selector 的 selectedKeys 获取准备就绪的通道集合
 * 针对事件处理通道
 * 连接就绪：
 * 调用 SocketChannel 的 isConnectionPending 判断否正在连接服务器端，如果是则调用 finishConnect 完成连接
 * 新建一个线程，通过 Scanner 接收用户输入并通过 SocketChannel 的 write 写入到通道
 * 设置监听 SocketChannel 的可读数据事件
 * 可读就绪：
 * 有从服务器端发送过来的信息，读取输出到屏幕上
 * 处理完事件后要清空 SelectionKey 集合，当下次该通道变成就绪时，Selector 才会再次将其放入 SelectionKey 中
 *
 * @author hyp
 * Project name is javaframework
 * Include in com.hyp.learn.netty.esp01
 * hyp create at 20-3-7
 **/
public class GroupChatClient {

    // 服务端地址
    private InetSocketAddress SERVER;

    // 用于接收数据的缓冲区
    private ByteBuffer rBuffer = ByteBuffer.allocate(1024);

    // 用于发送数据的缓冲区
    private ByteBuffer sBuffer = ByteBuffer.allocate(1024);

    // 用于监听通道事件
    private static Selector selector;

    // 用于编/解码 buffer
    private Charset charset = Charset.forName("UTF-8");

    public GroupChatClient(int port) {
        SERVER = new InetSocketAddress("localhost", port);
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 初始化客户端
    private void init() throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        selector = Selector.open();
        socketChannel.register(selector, SelectionKey.OP_CONNECT);
        socketChannel.connect(SERVER);
        while (true) {
            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            selectionKeys.forEach(this::handle);
            selectionKeys.clear(); // 清除处理过的事件
        }
    }

    /**
     * 处理事件
     */
    private void handle(SelectionKey selectionKey) {
        try {
            // 连接就绪事件
            if (selectionKey.isConnectable()) {
                SocketChannel client = (SocketChannel) selectionKey.channel();
                if (client.isConnectionPending()) {
                    client.finishConnect();
                    System.out.println("连接成功！");
                    // 启动线程监听客户端输入
                    new Thread(() -> {
                        while (true) {
                            try {
                                sBuffer.clear();
                                Scanner scanner = new Scanner(System.in);
                                String sendText = scanner.nextLine();
                                System.out.println(sendText);
                                sBuffer.put(charset.encode(sendText));
                                sBuffer.flip();
                                client.write(sBuffer);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                }
                // 注册可读事件
                client.register(selector, SelectionKey.OP_READ);
            }
            // 可读事件，有从服务器端发送过来的信息，读取输出到屏幕上
            else if (selectionKey.isReadable()) {
                SocketChannel client = (SocketChannel) selectionKey.channel();
                rBuffer.clear();
                int count = client.read(rBuffer);
                if (count > 0) {
                    String receiveText = new String(rBuffer.array(), 0, count);
                    System.out.println(receiveText);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        new GroupChatClient(6666);
    }
}
