package com.walk.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import java.util.ArrayList;
import java.util.Date;
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
    @Property
    private String surname;
    @Property
    @JsonIgnore
    private String password;
    @Property
    private String email;
    @Property
    private Date registered;
    @Property
    private String phoneNumber;
    @Property
    private String imageUrl;
    @Relationship(type = "Owns")
    private List<Walk> myWalks = new ArrayList<>();

    @Relationship(type = "Subscribe")
    private List<Walk> subscribed = new ArrayList<>();
    @Relationship
    private List<Walk> subscribeRequests = new ArrayList<>();
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<Walk> getSubscribed() {
        return subscribed;
    }

    public void setSubscribed(List<Walk> subscribed) {
        this.subscribed = subscribed;
    }

    public List<Walk> getMyWalks() {
        return myWalks;
    }

    public void setMyWalks(List<Walk> myWalks) {
        this.myWalks = myWalks;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Walk> getSubscribeRequests() {
        return subscribeRequests;
    }

    public void setSubscribeRequests(List<Walk> subscribeRequests) {
        this.subscribeRequests = subscribeRequests;
    }

}
