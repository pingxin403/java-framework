package com.hyp.learn.netty.inaction.esp01;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 定义一个 Charset 字符集用于解析数据
 * 定义两个 ByteBuffered 缓冲区用于收发数据
 * 定义一个 Map<String,Channel> 用于存放客户端集合
 * 定义一个 Selector 用于监听通道事件
 * 通过 ServerSocketChannel 的 open 方法打开一个 ServerSocketChannel 通道
 * 设置为非阻塞模式，并传递一个 InetSocketAddress 绑定端口
 * 注册 ServerSocketChannel 的接收就绪事件通道到 Selector
 * 循环监听事件
 * 调用 Selector 的 select 方法阻塞直到有准备就绪的通道
 * 调用 Selector 的 selectedKeys 获取准备就绪的通道集合
 * 针对事件处理通道
 * 接收就绪：
 * 代表有客户端要连接，通过 SelectionKey 对象获取 ServerSocketChannel
 * 调用 accept 方法接收请求客户端通道 SocketChannel
 * 为 SocketChannel 通道注册可读数据事件到 Selector
 * 将客户端 SocketChannel 添加到客户端集合 map 中
 * 可读就绪:
 * 代表客户端向服务器端发送了消息，通过 SelectionKey 对象获取 SocketChannel
 * 调用 read 方法将数据读入通道，通过 Charset 的 decode 将数据解码
 * 调用 dispatch 方法将消息转发给各个客户端
 * dispach方法：遍历客户端集合 map，通过 Channel 的 write 方法将消息发送给各个客户端
 * 处理完事件后要清空 SelectionKey 集合，当下次该通道变成就绪时，Selector 才会再次将其放入 SelectionKey 中
 *
 * @author hyp
 * Project name is javaframework
 * Include in com.hyp.learn.netty.esp01
 * hyp create at 20-3-7
 **/
public class GroupChatServer {

    // 用于监听通道事件
    private static Selector selector;
    private int port = 6666;
    // 用于字符集编解码
    private Charset charset = Charset.forName("UTF-8");
    // 用于接收数据的缓冲区
    private ByteBuffer rBuffer = ByteBuffer.allocate(1024);
    // 用于发送数据的缓冲区
    private ByteBuffer sBuffer = ByteBuffer.allocate(1024);
    // 用于存放客户端SocketChannel集合
    private Map<String, SocketChannel> clientMap = new HashMap<String, SocketChannel>();

    public GroupChatServer(int port) {
        this.port = port;
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        GroupChatServer server = new GroupChatServer(6666);
        server.listen();
    }

    // 初始化服务器
    private void init() throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress(port));
        selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("服务器启动，端口为：" + port);
    }

    /**
     * 服务器端轮询监听，select 方法会一直阻塞直到有相关事件发生或超时
     */
    public void listen() {
        while (true) {
            try {
                selector.select();   // 返回值为本次触发的事件数
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                selectionKeys.forEach(this::handle);
                selectionKeys.clear(); // 清除处理过的事件
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 处理事件
     */
    private void handle(SelectionKey selectionKey) {
        try {
            // 有客户端要连接
            if (selectionKey.isAcceptable()) {
                ServerSocketChannel server = (ServerSocketChannel) selectionKey.channel();
                SocketChannel client = server.accept();
                client.configureBlocking(false);
                client.register(selector, SelectionKey.OP_READ);
                clientMap.put(getClientName(client), client);
            }
            // 客户端发送了消息
            else if (selectionKey.isReadable()) {
                SocketChannel client = (SocketChannel) selectionKey.channel();
                rBuffer.clear();
                int bytes = client.read(rBuffer);
                if (bytes > 0) {
                    rBuffer.flip();
                    String receiveText = String.valueOf(charset.decode(rBuffer));
                    System.out.println(client.toString() + ":" + receiveText);
                    dispatch(client, receiveText);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 转发消息给各个客户端
     */
    private void dispatch(SocketChannel client, String info) throws IOException {
        if (!clientMap.isEmpty()) {
            for (Map.Entry<String, SocketChannel> entry : clientMap.entrySet()) {
                SocketChannel temp = entry.getValue();
                if (!client.equals(temp)) {
                    sBuffer.clear();
                    sBuffer.put(charset.encode(getClientName(client) + ":" + info));
                    sBuffer.flip();
                    temp.write(sBuffer);
                }
            }
        }
    }

    /**
     * 生成客户端名字
     */
    private String getClientName(SocketChannel client) {
        Socket socket = client.socket();
        return "[" + socket.getInetAddress().toString().substring(1) + ":" + Integer.toHexString(client.hashCode()) + "]";
    }
}
