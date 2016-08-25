package com.maxim.manager;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * Created by Максим on 24.08.2016.
 */
public class Friend {
    private String name;
    private Date date;
    private String interests;

    public Friend(String name,Date date,String interests){
        this.name=name;
        this.date= date;
        this.interests=interests;
    }

    public String getName(){return name;}
    public Date getDate(){return date;}
    public String getInterests(){return interests;}
}
