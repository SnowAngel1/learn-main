package com.learn.zookeeper.curator.service.impl;

import com.learn.zookeeper.curator.entity.Order;
import com.learn.zookeeper.curator.entity.Product;
import com.learn.zookeeper.curator.mapper.OrderMapper;
import com.learn.zookeeper.curator.mapper.ProductMapper;
import com.learn.zookeeper.curator.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

/**
 * @author ChenYP
 * @date 2023/7/26 17:15
 * @Describe
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private OrderMapper orderMapper;




    @Transactional
    @Override
    public void reduceStock(Integer id){
        //获取库存
        Product products = productMapper.selectOne(id);
        //模拟耗时的业务处理
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (products.getStock() <= 0){
            throw new RuntimeException("out of stock");
        }
        //2、减库存
        int  i  = productMapper.updateProduct(id);
        if (i == 1){
            Order order = new Order();
            order.setUserId(UUID.randomUUID().toString());
            order.setPId(id);
            orderMapper.insertOrder(order);
        }else {
            throw new RuntimeException("deduct stock fail,retry");
        }
    }
}
