package com.maxim.webjs.storage;

import com.maxim.manager.User;

import java.util.Collection;
import java.util.HashSet;

/**
 * Created by Максим on 24.08.2016.
 */
public class UsersCache{
    private static final UsersCache INSTANCE = new UsersCache();

    private HashSet<User> users =new HashSet<User>();

    public static UsersCache getInstance() {
        return INSTANCE;
    }

    public Collection<User> values() {
        return INSTANCE.users;
    }

    public void add(final User user) {
        INSTANCE.users.add(user);
    }

    public int delete(final int id) {
        for(User user:INSTANCE.users)
            if(user.getId()==id) {
                INSTANCE.users.remove(user);
                return 1;
            }
        return -1;
    }

    public User get(final int id) {
        User curUser=null;
        for(User user:INSTANCE.users)
            if(user.getId()==id) {
                curUser=user;
            }
        return curUser;
    }
}
