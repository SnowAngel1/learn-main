package com.learn.bio;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author ChenYP
 * @date 2023/7/20 15:18
 * @Describe 多线程处理请求，但是线程数是有限的，来一个客户端启动一个线程处理，线程数是有上限的，如果遇到执行的业务时间比较长，网络传输的速度慢的情况，其他线程就需要等到更长的时间
 */
public class ThreadServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket();
        //绑定端口号
        serverSocket.bind(new InetSocketAddress(8888));
        System.out.println("Server Start。。。");
        try {
            while (true){
                new Thread(new ServerTask(serverSocket.accept())).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            serverSocket.close();
        }


    }





    private static class ServerTask implements Runnable{

        private final Socket socket;

       public ServerTask(Socket socket){
           this.socket = socket;
       }

        @Override
        public void run() {
            try{
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                //获取客户端传入的消息
                String userName = objectInputStream.readUTF();
                System.out.println("Accept client message..." + userName);
                
                //响应客户端的消息
                objectOutputStream.writeUTF("Hello " + userName);
                objectOutputStream.flush();
                
                
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
