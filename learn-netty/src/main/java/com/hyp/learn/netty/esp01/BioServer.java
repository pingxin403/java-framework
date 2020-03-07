package com.hyp.learn.netty.esp01;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author hyp
 * Project name is javaframework
 * Include in com.hyp.learn.netty.bio
 * hyp create at 20-3-6
 **/
@SuppressWarnings("all")
public class BioServer {
    public static void main(String[] args) throws IOException {

        //数据库连接池
        ExecutorService pool = Executors.newCachedThreadPool();

        ServerSocket serverSocket = new ServerSocket(6666);

        System.out.println("服务器启动");

        while (true) {
            //等待客户端连接
            //阻塞位置1
            System.out.println("等待连接");
            final Socket socket = serverSocket.accept();

            System.out.println("连接到一个客户端");

            pool.execute(new Runnable() {
                @Override
                public void run() {
                    handler(socket);
                }
            });
        }
    }

    //一个handler，和客户端通信
    public static void handler(Socket socket) {
        byte[] bytes = new byte[1024];
        try {
            InputStream is = socket.getInputStream();
            int n = 0;
            //循环读取客户端数据
            //阻塞位置2
            while ((n = is.read(bytes)) > 0) {
                System.out.println("线程:" + Thread.currentThread().getId() + "\n线程名:" + Thread.currentThread().getName());
                System.out.println("read....");
                System.out.println(new String(bytes, 0, n));
            }
            System.out.println("接收数据完成");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("关闭客户端socket");
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
