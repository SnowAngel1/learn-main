package com.learn.concurrent.designpatterns.immutability;

/**
 * @author ChenYP
 * 写时复制保证线程安全
 */

public class SmsDemo {
    public static void main(String[] args) {
        // 创建短信服务商信息
        SmsInfo smsInfo = new SmsInfo("https://aliyun.com",180);

        // 线程——1设置短信服务商信息
        new Thread(new Runnable() {
            @Override
            public void run() {
                // smsInfo.setUrl("https://cloud.tencent.com");
                // try {
                //     Thread.sleep(100);
                // } catch (InterruptedException e) {
                //     throw new RuntimeException(e);
                // }
                // smsInfo.setMaxSizeInBytes(200);
                smsInfo.updateSmsInfo("https://cloud.tencent.com",200);
            }
        },"线程-1").start();



        // 线程——2读取短信服务商信息
        new Thread(()->{
            System.out.println(Thread.currentThread().getName() + "读取到的短信服务商信息：" + smsInfo);
        },"线程-2").start();


    }
}
