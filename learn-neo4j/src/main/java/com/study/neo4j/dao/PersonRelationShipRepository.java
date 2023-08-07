package com.study.neo4j.dao;

import com.study.neo4j.entity.PersonRelationShip;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

/**
 * @author：江南
 * @Description TODO
 * @since 1.0.0
 */
@Repository
public interface PersonRelationShipRepository extends Neo4jRepository<PersonRelationShip,Long> {

    @Query("match (n:person {name:{0}}),(m:person {name:{2}}) " +
        "create (n)-[r:人物关系{relation:{1}}]->(m)")
    void createNodeRelationShip(String from,String relation,String to);
}
