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

    private static int countFriends=0;

    public Friend(String name,Date birthday,String interests){
        this.name=name;
        this.birthday= birthday;
        this.interests=interests;
        this.id=countFriends++;
    }

    public String getName(){return name;}
    public Date getBirthday(){return birthday;}
    public String getInterests(){return interests;}
    public int getId(){return id;}
}
