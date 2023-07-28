package com.sharding.algorithm;

import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;

import java.util.Collection;

/**
 * @author ChenYP
 * @date 2023/7/18 18:27
 * @Describe
 */
public class MyRangeDSShardingAlgorithm implements RangeShardingAlgorithm<Long> {

    /**
     *
     * @param collection
     * @param rangeShardingValue 包含逻辑表名、分片列和分片列的条件范围
     * @return 返回目标结果，可以是多个
     */
    @Override
    public Collection<String> doSharding(Collection<String> collection, RangeShardingValue<Long> rangeShardingValue) {
        //实现按照 Between 进行范围分片
        //例如 select * from course where cid between 2000 and 3000
        Long lowerEndpoint = rangeShardingValue.getValueRange().lowerEndpoint();
        Long upperEndpoint = rangeShardingValue.getValueRange().upperEndpoint();
        //对于我们大部分奇偶分离场景，大部分范围查询都是两张表都查询
        return collection;
    }
}
