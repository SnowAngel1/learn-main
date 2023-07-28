package com.example.minio.config;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author：陈迎鹏
 * @Description TODO
 * @since 1.0.0
 */
@Configuration
public class MinioConfig {

    @Autowired
    private MinioProperties minioProperties;


    @Bean
    public MinioClient minioClient(){
        MinioClient minioClient = MinioClient.builder()
                .endpoint(minioProperties.getUrl())
                .credentials(minioProperties.getUserName(),minioProperties.getPassword())
                .build();
        return minioClient;
    }


}
