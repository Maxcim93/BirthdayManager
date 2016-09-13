package com.maxim.webjs.storage;

import com.maxim.manager.Friend;
import com.maxim.manager.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * Created by Максим on 13.09.2016.
 */
@Service
@Scope("singleton")
public class Storages {
    private Storage<User> usersStorage;
    private Storage<Friend> friendsStorage;

    @Autowired
    public Storages(Storage<User> usersStorage, Storage<Friend> friendsStorage){
        this.usersStorage=usersStorage;
        this.friendsStorage=friendsStorage;
    }
    public Storage<User> getUsersStorage(){
        return usersStorage;
    }

    public Storage<Friend> getFriendsStorage(){
        return friendsStorage;
    }
}
