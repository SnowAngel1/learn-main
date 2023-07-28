package com.study.neo4j.entity;

import lombok.Data;
import org.neo4j.ogm.annotation.*;

import java.io.Serializable;

/**
 * @author 江南
 * @date 2023/5/9
 */
@Data
@RelationshipEntity
public class CompanyRelationship implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @StartNode
    private CompanyNode startNode;

    @EndNode
    private CompanyNode endNode;

    @Property
    private String relation;

}
