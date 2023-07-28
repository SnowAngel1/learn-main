package com.sharding.algorithm;

import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author ChenYP
 * @date 2023/7/18 17:51
 * @Describe
 */
public class MyRangeTablesShardingAlgorithm implements RangeShardingAlgorithm<Long> {


    @Override
    public Collection<String> doSharding(Collection<String> collection, RangeShardingValue<Long> rangeShardingValue) {

        Long lowerEndpoint = rangeShardingValue.getValueRange().lowerEndpoint();
        Long upperEndpoint = rangeShardingValue.getValueRange().upperEndpoint();
        return Arrays.asList(rangeShardingValue.getLogicTableName() + "_1",rangeShardingValue.getLogicTableName()+ "_2");
    }
}
