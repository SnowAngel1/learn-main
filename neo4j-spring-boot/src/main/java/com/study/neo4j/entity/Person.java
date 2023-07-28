package com.study.neo4j.entity;

import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

import java.io.Serializable;

/**
 * @author：江南
 * @Description TODO
 * @since 1.0.0
 */
@Data
@NodeEntity(label = "person")
public class Person implements Serializable {
    private static final long serialVersionUID = 1038252731863289226L;

    @Id
    @GeneratedValue
    private Long id;

    @Property
    private String name;
}
