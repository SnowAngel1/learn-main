package com.study.neo4j.dao;

import com.study.neo4j.entity.CompanyNode;
import com.study.neo4j.entity.CompanyRelationship;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 江南
 * @date 2023/5/9
 */
@Repository
public interface CompanyShipRepository extends Neo4jRepository<CompanyRelationship,Long> {

    @Query("MATCH ( a:Company )-[ label ]-( b:Company ) RETURN   distinct label")
    List<CompanyNode> queryCompanyRelationship();
}
