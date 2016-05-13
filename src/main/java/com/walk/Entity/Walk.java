package com.walk.Entity;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bogdan on 5/13/2016.
 */
@NodeEntity
public class Walk {
    @GraphId
    private Long id;
    @Relationship
    private List<Point> path = new ArrayList<>();
    public List<Point> getPath() {
        return path;
    }

    public void setPath(List<Point> path) {
        this.path = path;
    }

    public void addPoint(int x, int y, String descript){
        Point point = new Point();
        point.x = x;
        point.y = y;
        point.description = descript;
        path.add(point);
    }
}
