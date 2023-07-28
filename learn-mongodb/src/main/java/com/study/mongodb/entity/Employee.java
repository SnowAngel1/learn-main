package com.study.mongodb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 * @author ChenYP
 * @date 2023/7/12 17:14
 * @describe
 */
@Document("emp")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Id //映射文档中的_id
    private Integer id;

    @Field("username")
    private String name;

    @Field
    private Integer age;

    @Field
    private Double salary;

    @Field
    private Date birthday;

}
