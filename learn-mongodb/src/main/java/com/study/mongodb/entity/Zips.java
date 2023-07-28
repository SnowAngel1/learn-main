package com.study.mongodb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author ChenYP
 * @date 2023/7/13 14:50
 * @describe
 */
@Data
@Document("zips")
@AllArgsConstructor
@NoArgsConstructor
public class Zips {

    @Id
    private String id;

    @Field
    private String city;

    @Field
    private Double[] loc;

    @Field
    private Integer pop;

    @Field
    private String state;
}
