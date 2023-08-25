package com.learn.concurrent;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

/**
 * @author ChenYP
 * 多线程使用文件输入输出流
 */

public class Piped {


    public static void main(String[] args) throws IOException {
        PipedWriter pipedWriter = new PipedWriter();
        PipedReader pipedReader = new PipedReader();
        // 将输出流和输入流进行连接，否则在使用时会抛出IOException异常
        pipedWriter.connect(pipedReader);
        Thread printThread = new Thread(new Print(pipedReader),"PrintThead");
        printThread.start();
        int receive = 0;
        try {

            while ((receive = System.in.read()) != -1){
                pipedWriter.write(receive);
            }
        }finally {
            pipedWriter.close();
        }
    }


    static class Print implements Runnable{

        private final PipedReader pipedReader;


        public Print(PipedReader pipedReader) {
            this.pipedReader = pipedReader;
        }

        @Override
        public void run() {
            int receive = 0;
            try {
                while ((receive = pipedReader.read()) != -1){
                    System.out.println((char)receive);
                }
            } catch (IOException e) {

            }

        }
    }
}
