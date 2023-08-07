package com.study.neo4j.dao;

import com.study.neo4j.entity.Person;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

/**
 * @author：江南
 * @Description TODO
 * @since 1.0.0
 */
@Repository
public interface PersonRepository extends Neo4jRepository<Person, Long> {
}
