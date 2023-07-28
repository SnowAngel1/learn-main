package com.study.neo4j.entity;

import lombok.Data;
import org.neo4j.ogm.annotation.*;

import java.io.Serializable;

/**
 * @author：江南
 * @Description TODO
 * @since 1.0.0
 */
@Data
@RelationshipEntity(type = "personRelationShip")
public class PersonRelationShip implements Serializable {
    private static final long serialVersionUID = -2045106466038560003L;

    @Id
    @GeneratedValue
    private Long id;

    @StartNode
    private Person parent;

    @EndNode
    private Person child;

    @Property
    private String relation;

}
