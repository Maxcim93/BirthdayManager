package com.maxim.webjs.storage;

import com.maxim.manager.User;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Максим on 04.09.2016.
 */
public class UserHibernateStorageTest {
    @Test
    public void addGetDeleteUser() throws Exception {
        final UserHibernateStorage storage = UserHibernateStorage.getInstance();
        final int id = storage.add(new User("name2", "080988098", "test@test.ru"));
        final User user = storage.get(id);
        assertEquals(id, user.getId());
        storage.delete(id);
        assertNull(storage.get(id));
        storage.close();
    }

}