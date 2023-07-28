package com.learn.bio;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author ChenYP
 * @date 2023/7/20 15:30
 * @Describe  使用线程池解决，线程有限的问题。这样也不是完美无缺，
 */
public class ServerPool {

    private static ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket();

        serverSocket.bind(new InetSocketAddress(8888));
        try {
            while (true){
                executorService.execute(new ServerTask(serverSocket.accept()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
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
