package com.walk.QueryResult;

import com.walk.Entity.Point;
import com.walk.Entity.User;
import com.walk.Entity.Walk;
import org.springframework.data.neo4j.annotation.QueryResult;

import java.util.Date;
import java.util.List;

/**
 * Created by Nazar on 14.05.2016.
 */
@QueryResult
public class WayAndUser {
    private int id;
    private String name;
    private String surname;
    private Date startDate;
    private String url;
    private Point start;
    private String title;
    private int peopleLeft;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Point getStart() {
        return start;
    }

    public void setStart(Point start) {
        this.start = start;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getPeopleLeft() {
        return peopleLeft;
    }

    public void setPeopleLeft(int peopleLeft) {
        this.peopleLeft = peopleLeft;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
}
