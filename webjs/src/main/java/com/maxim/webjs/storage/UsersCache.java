package com.maxim.webjs.storage;

import com.maxim.manager.Friend;
import com.maxim.manager.User;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ListIterator;

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

    public boolean delete(final int idUser) {
        Iterator<User> iterator=users.iterator();
        while(iterator.hasNext()){
            if(iterator.next().getId()==idUser) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    public User get(final int id) {
        User curUser=null;
        for(User user:INSTANCE.users)
            if(user.getId()==id) {
                curUser=user;
            }
        return curUser;
    }

    public boolean deleteFriend(int idUser,int idFriend){
        ListIterator<Friend> iterator=get(idUser).getFriends().listIterator();
        while(iterator.hasNext()){
            if(iterator.next().getId()==idFriend) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }
}
