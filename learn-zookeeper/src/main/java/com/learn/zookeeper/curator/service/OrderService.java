package com.learn.zookeeper.curator.service;

import com.learn.zookeeper.curator.entity.Product;

import java.util.List;

/**
 * @author ChenYP
 * @date 2023/7/26 17:15
 * @Describe
 */
public interface OrderService {

    void reduceStock(Integer id);


}
