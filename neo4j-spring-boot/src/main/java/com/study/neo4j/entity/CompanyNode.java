package com.study.neo4j.entity;

import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

/**
 * @author 江南
 * @date 2023/5/9
 */
@Data
@NodeEntity("Company")
public class CompanyNode {
    @GeneratedValue
    @Id
    private Long id;


    @Property("companyName")
    private String companyName;

    @Property("shareholdingRatio")
    private String shareholdingRatio;

    @Property("label")
    private String label;
}
