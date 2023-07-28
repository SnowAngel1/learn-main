package com.sentinel;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author：江南
 * @Description TODO
 * @since 1.0.0
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, DruidDataSourceAutoConfigure.class})
public class SpringCloudAlibabaSentinelApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudAlibabaSentinelApplication.class, args);
    }
}
