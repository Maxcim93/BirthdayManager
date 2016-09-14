package com.maxim.webjs.service;

import com.maxim.manager.Friend;
import com.maxim.manager.User;
import com.maxim.webjs.storage.FriendHibernateStorage;
import com.maxim.webjs.storage.Storage;
import com.maxim.webjs.storage.Storages;
import com.maxim.webjs.storage.UserHibernateStorage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * Created by Максим on 14.09.2016.
 */
@Configuration
@ComponentScan(basePackages = "com.maxim")
public class AppSpringConfig {
    @Bean
    public Storage<User> getUsersStorage(){
        return new UserHibernateStorage();
    }

    @Bean
    public Storage<Friend> getFriendsStorage(){
        return new FriendHibernateStorage();
    }

    @Bean
    @Scope("singleton")
    public Storages getStorages(Storage<User> userStorage,Storage<Friend> friendStorage){
        return new Storages(userStorage,friendStorage);
    }

}
