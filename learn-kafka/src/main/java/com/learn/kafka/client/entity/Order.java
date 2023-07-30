package com.learn.kafka.client.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ChenYP
 * @date 2023/7/28 18:35
 * @Describe
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private Integer orderId;

    private Integer productId;

    private Integer productNum;

    private Double orderAmount;
}
