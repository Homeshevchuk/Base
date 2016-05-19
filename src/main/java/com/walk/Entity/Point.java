package com.walk.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

/**
 * Created by Bogdan on 5/13/2016.
 */
@NodeEntity
public class Point {
    @GraphId
    @JsonIgnore
    private Long id;
    @Property
    int pointId;
    @Property
    private double x;
    @Property
    private double y;

    public Point(){

    }
    public Point(int x, int y, String description){
        this.pointId = pointId;
        this.x = x;
        this.y = y;
    }
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public Long getId() {
        return id;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }


    public int getPointId() {
        return pointId;
    }

    public void setPointId(int pointId) {
        this.pointId = pointId;
    }
}
