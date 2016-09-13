package com.maxim.webjs.storage;

import com.maxim.manager.Friend;
import com.maxim.manager.User;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Максим on 13.09.2016.
 */
public class StoragesTest {
    @Test
    public void getStoragesSpringIoC() throws Exception {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("spring_storages.xml");

        Storages storages=context.getBean(Storages.class);
        Storage<User> userStorage=storages.getUsersStorage();
        Storage<Friend> friendStorage=storages.getFriendsStorage();

        Assert.assertTrue(Storages.class.isInstance(storages));
        Assert.assertTrue(UserHibernateStorage.class.isInstance(userStorage));
        Assert.assertTrue(FriendHibernateStorage.class.isInstance(friendStorage));
    }

    @Test
    public void storagesSingletonSpringIoC() throws Exception {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("spring_storages.xml");

        Storages storagesOneRef=context.getBean(Storages.class);
        Storages storagesTwoRef=context.getBean(Storages.class);
        Assert.assertTrue(storagesOneRef==storagesTwoRef);
    }
}