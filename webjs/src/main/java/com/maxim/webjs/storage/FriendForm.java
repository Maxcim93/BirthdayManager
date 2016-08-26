package com.maxim.webjs.storage;

import java.util.Date;

/**
 * Created by Максим on 26.08.2016.
 */
public class FriendForm {
    private String name;
    private Date birthday;
    private String interests;

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public Date getBirthday(){return birthday;}

    public void setBirthday(Date birthday){this.birthday=birthday;}

    public String getInterests(){return interests;}

    public void setInterests(String interests){this.interests=interests;}
}
