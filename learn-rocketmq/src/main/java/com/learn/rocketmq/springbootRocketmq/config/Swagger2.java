package com.learn.rocketmq.springbootRocketmq.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springChenYP.documentation.builders.ApiInfoBuilder;
import springChenYP.documentation.builders.PathSelectors;
import springChenYP.documentation.service.ApiInfo;
import springChenYP.documentation.spi.DocumentationType;
import springChenYP.documentation.spring.web.plugins.Docket;
import springChenYP.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author ：ChenYP
 * @date ：Created in 2020/11/5
 * @description:
 **/

@Configuration
@EnableSwagger2
public class Swagger2 {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.didispace.web"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring Boot中使用Swagger2构建RESTful APIs")
                .description("SpringBoot集成RocketMQ")
                .contact("ChenYP")
                .version("1.0")
                .build();
    }
}
