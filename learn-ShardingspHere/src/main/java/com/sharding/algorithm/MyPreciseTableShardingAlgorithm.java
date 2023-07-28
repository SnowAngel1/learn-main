package com.sharding.algorithm;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;

/**
 * @author ChenYP
 * @date 2023/7/18 17:59
 * @Describe
 */
public class MyPreciseTableShardingAlgorithm implements PreciseShardingAlgorithm<Long> {
    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Long> shardingValue) {
        //实现按照 = 或 IN 进行精确分片
        //例如 select * from course where cid = 1 or cid in (1,2)
        //实现course_$->{cid%2+1} 分表策略
        BigInteger shardingValueB = BigInteger.valueOf(shardingValue.getValue());
        BigInteger resB = (shardingValueB.mod(new BigInteger("2"))).add(new BigInteger("1"));
        String key = shardingValue.getLogicTableName()+ "_"+ resB;
        if (collection.contains(key)){
            return  key;
        }
        throw new UnsupportedOperationException("route " + key + "is not supported,  please check your config");
    }
}
