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
    private LinkedList<Friend> friends=new LinkedList<Friend>();
    private int id;

    private static int countUsers=0;

    public User(String name,String number,String email){
        this.name=name;
        this.number=number;
        this.email=email;
        this.id=countUsers++;
    }

    public String getNumber(){return number;}
    public String getEmail(){return email;}
    public String getName(){return name;}
    public void addFriend(Friend friend){friends.add(friend);}
    public LinkedList<Friend> getFriends(){return friends;}
    public int getId(){return id;}

    public Friend getFriend(int id){
        Friend retFriend=null;
        for(Friend friend: friends){
            if(friend.getId()==id) {
                retFriend = friend;
                break;
            }
        }
        return retFriend;
    }
}
