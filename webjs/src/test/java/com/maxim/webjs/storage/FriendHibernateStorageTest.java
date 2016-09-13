package com.maxim.webjs.storage;

import com.maxim.manager.Friend;
import com.maxim.manager.User;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by Максим on 04.09.2016.
 */
public class FriendHibernateStorageTest {
    @Test
    public void addGetDeleteFriend() throws Exception {
        //create user
        final UserHibernateStorage storageUsers = new UserHibernateStorage();
        final int idUser = storageUsers.add(new User("name2", "080988098", "test@test.ru"));
        //create friends
        final FriendHibernateStorage storageFriends = new FriendHibernateStorage();
        final int idFriend1 = storageFriends.add(new Friend("nameFriend",
                                                            new Date(980239L),
                                                            "any stuff",
                                                            new User(idUser,null,null,null)));
        final int idFriend2 = storageFriends.add(new Friend("nameFriend",
                                                            new Date(980239L),
                                                            "any stuff",
                                                            new User(idUser,null,null,null)));
        //get friends
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("iduser",idUser);

        Collection<Friend> friends=storageFriends.get(params);

        List<Integer> collIdFriend=new ArrayList<Integer>();
        for(Friend friend:friends) {
            collIdFriend.add(friend.getId());
        }
        Integer[] expectedValues=new Integer[]{idFriend1,idFriend2};
        Integer[] getFriendIds=new Integer[collIdFriend.size()];
        collIdFriend.toArray(getFriendIds);

        //check identity added friends and got friends
        assertArrayEquals(expectedValues, getFriendIds);

        //delete friends
        storageFriends.delete(idFriend1);
        storageFriends.delete(idFriend2);

        //get deleted friends
        Collection<Friend> deletedFriends = storageFriends.get(params);

        //check collection is empty
        assertEquals(true,deletedFriends.isEmpty());

        storageUsers.close();
        storageFriends.close();
    }

}