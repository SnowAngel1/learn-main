package com.learn.zookeeper.curator.mapper;

import com.learn.zookeeper.curator.entity.Order;
import com.learn.zookeeper.curator.entity.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ChenYP
 * @date 2023/7/26 16:29
 * @Describe
 */
public interface OrderMapper {

    Integer insertOrder(Order order);

}
