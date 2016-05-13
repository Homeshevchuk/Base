package com.walk.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

/**
 * Created by Bogdan on 5/13/2016.
 */
@NodeEntity
public class User {
    @GraphId
    private Long id;
    @Property
    private String name;

    @Relationship
    private List<Walk> walks;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Walk> getWalks() {
        return walks;
    }

    public void setWalks(List<Walk> walks) {
        this.walks = walks;
    }
}
