package com.learn.bio;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author ChenYP
 * @date 2023/7/20 14:43
 * @Describe
 */
public class Client {
    public static void main(String[] args) throws IOException {

        //客户端启动必备
        Socket socket = null;

        ObjectInputStream objectInputStream = null;
        ObjectOutputStream objectOutputStream = null;


        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1",8888);


        try {
            socket = new Socket();
            //连接服务器
            socket.connect(inetSocketAddress);
            System.out.println("Connect Server success!!");
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            System.out.println("Ready send message");

            objectOutputStream.writeUTF("江南");
            objectOutputStream.flush();

            //接收服务器的返回响应
            System.out.println(objectInputStream.readUTF());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if(socket != null){
                socket.close();
            }
            if (objectOutputStream != null){
                objectOutputStream.close();
            }
            if (objectInputStream != null){
                objectInputStream.close();
            }
        }


    }
}
