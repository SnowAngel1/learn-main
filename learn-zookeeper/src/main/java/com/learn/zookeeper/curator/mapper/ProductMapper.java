package com.learn.zookeeper.curator.mapper;

import com.learn.zookeeper.curator.entity.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ChenYP
 * @date 2023/7/26 17:36
 * @Describe
 */
public interface ProductMapper {

    Product selectOne(@Param("id")Integer id);


    Integer updateProduct(@Param("id")Integer id);
}
