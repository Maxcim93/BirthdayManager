package com.maxim.webjs.storage;

import com.maxim.manager.Friend;
import com.maxim.manager.User;

/**
 * Created by Максим on 13.09.2016.
 */
public class Storages {
    private Storage<User> usersStorage;
    private Storage<Friend> friendsStorage;

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
