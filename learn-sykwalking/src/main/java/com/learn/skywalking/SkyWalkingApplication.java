package com.learn.skywalking;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ChenYP
 */
@SpringBootApplication
@MapperScan("com.learn.skywalking.dao")
public class SkyWalkingApplication {
    public static void main(String[] args) {
        SpringApplication.run(SkyWalkingApplication.class,args);
    }
}
