package com.study;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author ChenYP
 * @date 2023/7/12 16:36
 * @describe
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, DruidDataSourceAutoConfigure.class})
public class StudyMongodbApplication {
    public static void main(String[] args) {
        SpringApplication.run(StudyMongodbApplication.class,args);
    }
}
