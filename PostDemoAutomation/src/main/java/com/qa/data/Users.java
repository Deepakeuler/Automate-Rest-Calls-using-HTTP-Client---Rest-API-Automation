package com.qa.data;

public class Users {

    //I'll define user data here, w.r.t to payload we'll create an equivalent java class
    //pojo -> plain old java object
    String name;
    String job;
    String id;
    String createdAt;

    public Users(){

    }
    public Users(String name, String job){
        this.name = name;
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public void setName(String name) {
        this.name = name;
    }
}
