package com.maxim.webjs.storage;

import com.maxim.manager.User;
import com.maxim.webjs.service.Settings;

import java.sql.*;
import java.util.*;

/**
 * Created by Максим on 29.08.2016.
 */
public class UserDbStorage implements Storage<User> {
    private static final UserDbStorage INSTANCE = new UserDbStorage();
    public static UserDbStorage getInstance() {
        return INSTANCE;
    }

    private final Connection connection;

    public UserDbStorage () {
        final Settings settings = Settings.getInstance();
        try {
            this.connection = DriverManager.getConnection(settings.value("jdbc.url"), settings.value("jdbc.username"), settings.value("jdbc.password"));
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public Collection<User> values() {
        final List<User> users = new ArrayList<User>();
        try (final Statement statement = this.connection.createStatement();
             final ResultSet rs = statement.executeQuery("select * from client")) {
            while (rs.next()) {
                users.add(new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("number"),
                        rs.getString("email")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    };

    public int add(final User user){
        try (final PreparedStatement statement = this.connection.prepareStatement("insert into client (name,number,email) values (?,?,?)",
                Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1,user.getName());
            statement.setString(2,user.getNumber());
            statement.setString(3,user.getEmail());
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException("Could not create new user");
    };

    public void edit(final User user){
        try (final PreparedStatement statement = this.connection.prepareStatement("update client set name=?,number=?,email=? where id=?",
                Statement.RETURN_GENERATED_KEYS)) {

            statement.setInt(4,user.getId());
            statement.setString(1,user.getName());
            statement.setString(2,user.getNumber());
            statement.setString(3,user.getEmail());
            statement.executeUpdate();
        } catch (SQLException e) {
            Throwable newE=new Exception("Could not update user");
            e.initCause(newE);
        }
    };

    public void delete(final int id){
        try (final PreparedStatement statement = this.connection.prepareStatement("delete from client where id=?",
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            Throwable newE=new Exception("Could not delete user");
            e.initCause(newE);
        }
    }

    public User get(final int id){
        User user=null;
        try (final PreparedStatement statement = this.connection.prepareStatement("select * from client where id=?")) {
            statement.setInt(1,id);
            final ResultSet rs=statement.executeQuery();
            user=new User(rs.getInt(id),rs.getString("name"),rs.getString("number"),rs.getString("email"));
        } catch (SQLException e) {
            Throwable newE=new Exception("Could not create new user");
            e.initCause(newE);
        }
        return user;
    };

    public Collection<User> get(Map<String,Object> params){return null; }

    public void close(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
