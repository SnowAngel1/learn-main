package com.learn.zookeeper.curator.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ChenYP
 * @date 2023/7/26 16:54
 * @Describe
 */
@Data
public class Product implements Serializable {
    private static final long serialVersionUID = -4517797119767060470L;

    private Integer id;

    private String productName;

    private Integer stock;

    private Integer version;
}
