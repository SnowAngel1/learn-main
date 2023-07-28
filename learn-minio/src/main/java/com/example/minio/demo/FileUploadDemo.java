package com.example.minio.demo;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.UploadObjectArgs;
import io.minio.errors.*;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author：陈迎鹏
 * @Description Minio 分布式文件存储，上传
 * @since 1.0.0
 */
public class FileUploadDemo {
    public static void main(String[] args) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {

        //创建连接
        MinioClient client = MinioClient.builder().endpoint("http://192.168.3.203:9000").credentials("admin","12345678").build();


        //判断并创建桶
        String bucket = "hellominio";
        boolean bucketExists = client.bucketExists(BucketExistsArgs.builder().bucket(bucket).build());
        if (!bucketExists){
            client.makeBucket(MakeBucketArgs.builder().bucket(bucket).build());
        }

        //上传文件
        client.uploadObject(UploadObjectArgs.builder().bucket(bucket).object("zhangyouli.jpg").filename("zhangyouli.jpg").build());
        System.out.println("文件上传成功");


    }
}
