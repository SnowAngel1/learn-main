package com.sharding;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude={DruidDataSourceAutoConfigure.class})
@MapperScan("com.sharding.mapper")
public class ShardingApp {
    public static void main(String[] args) {
        SpringApplication.run(ShardingApp.class,args);
    }
}
