package com.walk;

import com.walk.Entity.User;
import com.walk.Entity.Walk;
import com.walk.QueryResult.UserRequest;
import com.walk.QueryResult.WayAndUser;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Bogdan on 5/13/2016.
 */
@Repository
public interface UserRepository extends GraphRepository<User> {
    User findByName(String name);
    @Query("MATCH (n:Walk)\n" +
            "WHERE n.completed = false\n" +
            "MATCH (n)-[:PATH]->(k:Point)\n" +
            "WHERE k.pointId = 0\n" +
            "MATCH (m:User)-[:Owns]->(n)\n" +
            "OPTIONAL MATCH (f:User)-[r:Subscribe]->(n)\n" +
            "Return id(n) as id, m.imageUrl as url,n.startDate as startDate,m.name as name,m.surname as surname,k as start, n.title as title,n.maxUsers-count(r) as peopleLeft")
    List<WayAndUser> findAllActive();
    @Query("MATCH (m:User)-[:Owns]->(n:Walk)\n" +
            "WHERE id(m) = {0}\n" +
            "MATCH (f:User)-[:SUBSCRIBE_REQUESTS]->(n)\n" +
            "RETURN f as user, n as walk")
    List<UserRequest> getRequests(Long ownerId);
}
