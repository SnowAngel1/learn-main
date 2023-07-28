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
@NodeEntity(label = "star")
public class StarRelation implements Serializable {
    private static final long serialVersionUID = -6184265432454877846L;


    @Id
    @GeneratedValue
    private Long id;

    @StartNode
    private String subject;

    @EndNode
    private String object;

    @Property
    private String relation;

    @Property
    private String name;


}
