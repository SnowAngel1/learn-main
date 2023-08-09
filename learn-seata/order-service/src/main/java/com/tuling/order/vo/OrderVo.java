package com.tuling.order.vo;

import lombok.Data;

/**
 * @author ChenYP
 */
@Data
public class OrderVo {
    private String userId;
    /**商品编号**/
    private String commodityCode;
    
    private Integer count;
    
    private Integer money;
}
