package com.study.mongodb;

import com.study.StudyMongodbApplicationTest;
import com.study.mongodb.entity.Zips;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.List;
import java.util.Map;

/**
 * @author ChenYP
 * @date 2023/7/13 14:42
 * @describe mongodb聚合操作
 */
public class AggTest extends StudyMongodbApplicationTest {

    @Autowired
    private MongoTemplate mongoTemplate;



    @Test
    public void testAgg1() {
    //     $group
        GroupOperation groupOperation = Aggregation.group("state").sum("pop").as("totalPop");
    //     $match
        MatchOperation matchOperation = Aggregation.match(Criteria.where("totalPop").gt(10 * 100 * 10000));

        // $sort

        SortOperation sortOperation = Aggregation.sort(Sort.Direction.DESC,"totalPop");


    //     按照顺序组合上面两个阶段
        TypedAggregation<Zips> typedAggregation = new TypedAggregation<>(Zips.class,groupOperation, matchOperation,sortOperation);
    //     调用对应API获取结果
        AggregationResults<Map> results = mongoTemplate.aggregate(typedAggregation, Map.class);
        List<Map> mappedResults = results.getMappedResults();


        for (Map mappedResult : mappedResults) {
            System.out.println(mappedResult);
        }
    }

    @Test
    public void testAgg2(){
        GroupOperation groupOperation1 = Aggregation.group("state","city").sum("pop").as("cityPop");

        GroupOperation groupOperation2 = Aggregation.group("_id.state").avg("cityPop").as("avgCityPop");

        SortOperation sortOperation = Aggregation.sort(Sort.Direction.DESC, "avgCityPop");


        TypedAggregation<Zips> typedAggregation = new TypedAggregation<>(Zips.class, groupOperation1, groupOperation2, sortOperation);

        AggregationResults<Map> aggregate = mongoTemplate.aggregate(typedAggregation, Map.class);
        aggregate.getMappedResults().forEach(System.out::println);
    }


    @Test
    public void testAgg3(){
        //$group
        GroupOperation groupOperation = Aggregation.group("state","city").sum("pop").as("pop");

        //$sort
        SortOperation sortOperation = Aggregation.sort(Sort.Direction.ASC,"pop");

        //$group
        GroupOperation groupOperation1 = Aggregation.group("_id.state")
                .last("_id.city").as("biggestCity")
                .last("pop").as("biggestPop")
                .first("_id.city").as("smallestCity")
                .first("pop").as("smallestPop");
        //project
        ProjectionOperation projectionOperation = Aggregation
                .project("biggestCity","smallestCity","state")
                .andExclude("_id")
                .andExpression("{ name: \"$biggestCity\", pop: \"$biggestPop\" }").as("biggestCity")
                .andExpression("{ name: \"$smallestCity\", pop: \"$smallestPop\" }").as("$smallestCity")
                .andExpression("_id").as("state");
        //$sort
        SortOperation sortOperation1 = Aggregation.sort(Sort.Direction.ASC,"state");


        TypedAggregation<Zips> typedAggregation = new TypedAggregation<>(Zips.class, groupOperation, sortOperation, groupOperation1,projectionOperation,sortOperation1);


        AggregationResults<Map> aggregate = mongoTemplate.aggregate(typedAggregation, Map.class);
        aggregate.getMappedResults().forEach(System.out::println);
    }
}
