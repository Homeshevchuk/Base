package com.walk.Entity;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 * Created by Bogdan on 5/13/2016.
 */
@NodeEntity
public class Point {
    @GraphId
    private Long id;
    int x;
    int y;
    String description;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
