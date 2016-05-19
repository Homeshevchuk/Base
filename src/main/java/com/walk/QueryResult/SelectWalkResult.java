package com.walk.QueryResult;

import com.walk.Entity.User;
import com.walk.Entity.Walk;
import org.springframework.data.neo4j.annotation.QueryResult;

import java.util.List;

/**
 * Created by Nazar on 14.05.2016.
 */
@QueryResult
public class SelectWalkResult {
    private Walk walk;
    User owner;
    List<User> subscribers;

    public Walk getWalk() {
        return walk;
    }

    public void setWalk(Walk walk) {
        this.walk = walk;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<User> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(List<User> subscribers) {
        this.subscribers = subscribers;
    }
}
