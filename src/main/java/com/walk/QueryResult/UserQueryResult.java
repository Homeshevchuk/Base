package com.walk.QueryResult;

import com.walk.Entity.User;
import org.springframework.data.neo4j.annotation.QueryResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Nazar on 15.05.2016.
 */

public class UserQueryResult {
    User owner;
    List<UserRequest> list = new ArrayList<>();

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<UserRequest> getList() {
        return list;
    }

    public void setList(List<UserRequest> list) {
        this.list = list;
    }
}
