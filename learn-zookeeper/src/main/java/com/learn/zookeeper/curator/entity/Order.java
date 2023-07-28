package com.learn.zookeeper.curator.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ChenYP
 * @date 2023/7/26 17:32
 * @Describe
 */
@Data
public class Order implements Serializable {

    private static final long serialVersionUID = -2321198326047295922L;


    private Integer id;

    private Integer pId;

    private String userId;
}
