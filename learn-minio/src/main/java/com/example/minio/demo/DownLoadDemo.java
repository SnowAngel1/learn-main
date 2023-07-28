package com.example.minio.demo;

import io.minio.DownloadObjectArgs;
import io.minio.MinioClient;
import io.minio.errors.*;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author：陈迎鹏
 * @Description minio分布式存储，下载
 * @since 1.0.0
 */
public class DownLoadDemo {
    public static void main(String[] args) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        //创建连接
        MinioClient minioClient = MinioClient.builder().endpoint("http://192.168.3.203:9000").credentials("admin","12345678").build();


        String bucket = "hellominio";

        minioClient.downloadObject(DownloadObjectArgs.builder().bucket(bucket).object("zhangyouli.jpg").filename("learn-minio/zhangyouli.jpg").build());



    }
}
