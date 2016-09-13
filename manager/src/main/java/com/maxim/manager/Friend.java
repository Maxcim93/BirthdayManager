package com.maxim.manager;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * Created by Максим on 24.08.2016.
 */
public class Friend {
    private int id;
    private String name;
    private Date birthday;
    private String interests;
    private User user;


    public Friend(){

    }
    public Friend(String name,Date birthday,String interests,User user){
        this.name=name;
        this.birthday= birthday;
        this.interests=interests;
        this.user=user;
    }

    public Friend(int id,String name,Date birthday,String interests, User user){
        this.id=id;
        this.name=name;
        this.birthday= birthday;
        this.interests=interests;
        this.user=user;
    }

    public int getId(){return id;}
    public void setId(int id){this.id=id;}

    public String getName(){return name;}
    public void setName(String name){this.name=name;}

    public Date getBirthday(){return birthday;}
    public void setBirthday(Date birthday){this.birthday=birthday;}

    public String getInterests(){return interests;}
    public void setInterests(String interests){this.interests=interests;}

    public User getUser(){return user;}
    public void setUser(User user){this.user=user;}
}
