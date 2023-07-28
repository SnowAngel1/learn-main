package com.mall.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author：江南
 * @Description TODO
 * @since 1.0.0
 */
@SpringBootApplication
@MapperScan("com.mall.user.dao")
public class MallUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallUserApplication.class);
    }
}
