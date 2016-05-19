package com.walk.QueryResult;

import com.walk.Entity.User;
import com.walk.Entity.Walk;
import org.springframework.data.neo4j.annotation.QueryResult;

/**
 * Created by Nazar on 15.05.2016.
 */
@QueryResult
public class UserRequest {
    User user;
    Walk walk;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Walk getWalk() {
        return walk;
    }

    public void setWalk(Walk walk) {
        this.walk = walk;
    }
}
