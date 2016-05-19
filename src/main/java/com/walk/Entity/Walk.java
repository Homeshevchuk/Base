package com.walk.Entity;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.neo4j.annotation.QueryResult;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Bogdan on 5/13/2016.
 */
@ComponentScan(basePackages = "com.walk")
@NodeEntity
@QueryResult
public class Walk {
    @GraphId
    private Long id;
    @Property
   private Boolean completed;
    @Property
    private Date startDate;
    @Property
    private  String title;
    @Property
    private int maxUsers;
    @Property
    private String description;
    @Relationship
    private List<Point> path = new LinkedList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Point> getPath() {
        return path;
    }

    public void setPath(List<Point> path) {
        this.path = path;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getMaxUsers() {
        return maxUsers;
    }

    public void setMaxUsers(int maxUsers) {
        this.maxUsers = maxUsers;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void addPoint(double x, double y, String descript){
        Point point = new Point();
        point.setPointId(path.size());
        point.setX(x);
        point.setY(y);
        path.add(point);
    }
}
