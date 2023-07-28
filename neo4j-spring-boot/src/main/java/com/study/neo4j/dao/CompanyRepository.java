package com.study.neo4j.dao;

import com.study.neo4j.entity.CompanyNode;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

/**
 * @author 江南
 * @date 2023/5/9
 */
public interface CompanyRepository extends Neo4jRepository<CompanyNode,Long> {

    @Query("MATCH (n:Company) RETURN n,n.label")
    List<CompanyNode> queryAllNode(String name);



}
