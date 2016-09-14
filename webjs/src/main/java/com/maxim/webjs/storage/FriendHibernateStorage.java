package com.maxim.webjs.storage;

import com.maxim.manager.Friend;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;

/**
 * Created by Максим on 30.08.2016.
 */
public class FriendHibernateStorage implements Storage<Friend> {

    private final SessionFactory factory;

    public FriendHibernateStorage() {
        factory = new Configuration().configure().buildSessionFactory();
    }

    public Collection<Friend> values(){
        return null;
    }

    public int add(final Friend friend){
        final Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.save(friend);
            return friend.getId();
        } finally {
            tx.commit();
            session.close();
        }
    }

    public void edit(final Friend friend){}

    public void delete(final int id){
        final Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.delete(new Friend(id,null,null,null,null));
        } finally {
            tx.commit();
            session.close();
        }
    }

    public Friend get(final int id){
        return null;
    }

    public Collection<Friend> get(Map<String,Object> params){
        if(params==null || params.isEmpty())
            throw new IllegalArgumentException("Params are not defined");
        Integer idUser=(Integer) params.get("iduser");

        final Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            final Query query = session.createQuery("from Friend as friend where friend.user.id=:id_user");
            query.setInteger("id_user", idUser);

            return query.list();
        } finally {
            tx.commit();
            session.close();
        }
    }

    public void close(){
        this.factory.close();
    };
}
