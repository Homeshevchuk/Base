package com.walk;

import com.walk.Entity.Walk;
import com.walk.QueryResult.SelectWalkResult;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import java.util.List;

/**
 * Created by Nazar on 14.05.2016.
 */
public interface WalksRepository extends GraphRepository<Walk> {
    @Query("MATCH (n:Walk)\n" +
            "WHERE id(n) = {0}\n" +
            "MATCH (m:User)-[:Owns]->(n)\n" +
            "OPTIONAL MATCH (f:User)-[:Subscribe]->(n)\n" +
            "RETURN m as owner, collect(f) as subscribers")
    SelectWalkResult getWalk(Long id);

    List<Walk> findByCompleted(Boolean completed);


}
