package com.learn.zookeeper.curator;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author ChenYP
 * @date 2023/7/26 16:18
 * @Describe
 */
@SpringBootApplication
@MapperScan("com.learn.zookeeper.curator.mapper")
public class CuratorApplication {
    public static void main(String[] args) {
        SpringApplication.run(CuratorApplication.class,args);
    }

}
