package com.learn.bio;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author ChenYP
 * @date 2023/7/20 14:43
 * @Describe 单线程服务端；处理客户端请求使用一个线程处理，其他客户端会阻塞等待
 */
public class SingleServer {
    public static void main(String[] args) throws IOException {
        //服务端启动必备
        ServerSocket serverSocket = new ServerSocket();
        //表示服务端在哪个端口监听
        serverSocket.bind(new InetSocketAddress(8888));
        System.out.println("Start Server.......");
        int connectCount = 0;

        while (true){
            //阻塞的接收操作
            try (Socket socket = serverSocket.accept()) {
                System.out.println("accept client socket ..... total" + (++connectCount));
                ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                String userName = inputStream.readUTF();
                System.out.println("Accept client message " + userName);


                objectOutputStream.writeUTF("Hello, " + userName);
                objectOutputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
