package com.maxim.webjs.storage;

import com.maxim.manager.User;
import com.maxim.webjs.service.Settings;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.sql.*;
import java.util.*;

/**
 * Created by Максим on 29.08.2016.
 */
public class UserHibernateStorage implements Storage<User>{
    private static final UserHibernateStorage INSTANCE =new UserHibernateStorage();
    public static UserHibernateStorage getInstance(){return INSTANCE;}

    private final SessionFactory factory;

    public UserHibernateStorage() {
        factory = new Configuration().configure().buildSessionFactory();
    }


    public Collection<User> values() {
        final Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            return session.createQuery("from User").list();
        } finally {
            tx.commit();
            session.close();
        }
    }

    public int add(final User user){
        final Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.save(user);
            return user.getId();
        } finally {
            tx.commit();
            session.close();
        }
    }

    public void edit(final User user){
    }

    public void delete(final int id){
        final Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.delete(new User(id, null, null,null));
        } finally {
            tx.commit();
            session.close();
        }
    }

    public User get(final int id){
        final Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            return (User) session.get(User.class, id);
        } finally {
            tx.commit();
            session.close();
        }
    };

    public Collection<User> get(Map<String,Object> params){return null; }

    public void close(){
        this.factory.close();
    }
}
