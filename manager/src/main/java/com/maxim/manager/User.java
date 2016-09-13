package com.maxim.manager;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Created by Максим on 24.08.2016.
 */
public class User {
    private String number;
    private String email;
    private String name;
    private int id;

    public User(){}

    public User(int id,String name,String number,String email){
        this.id=id;
        this.name=name;
        this.number=number;
        this.email=email;
    }

    public User(String name,String number,String email){
        this.name=name;
        this.number=number;
        this.email=email;
    }

    public String getNumber(){return number;}
    public void setNumber(String number){this.number=number;}

    public String getEmail(){return email;}
    public void setEmail(String email){this.email=email;}

    public String getName(){return name;}
    public void setName(String name){this.name=name;}

    public int getId(){return id;}
    public void setId(int id){this.id=id;}
}
