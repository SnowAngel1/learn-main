package com.study.neo4j.dao;

import com.study.neo4j.entity.StarRelation;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author：江南
 * @Description TODO
 * @since 1.0.0
 */
@Repository
public interface StarRelationRepository extends Neo4jRepository<StarRelation,Long> {


    @Query("match (n:star),(m:starRelation),(s:star) where n.name = m.subject and s.name = m.object" +
            "create (n)-[r:明星关系{relation:m.relation}]->(s) return n.name,m.relation,s.name")
    List<StarRelation> createStarRealtion();


    @Query("match (n:star{name:{0}}),(m:starRelation),(s:star) where  n.name= m.subject and s.name=m.object\n" +
            "create (n)-[r:明星关系2 {relation:m.relation}]->(s) return m")
    List<StarRelation> createStarRealtionByName(String name);


    @Query("match (n:star{name:{0}}),(m:starRelation),(s:star) where  n.name = m.subject and s.name = m.object return n")
    List<StarRelation> qyeryStarRealtionByName(String name);


    @Query("match (n:person{name:{0}}) return n")
    List<StarRelation> qyeryStarByName(String name);
}

