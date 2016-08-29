package com.maxim.webjs.storage;

import com.maxim.manager.Friend;
import com.maxim.manager.User;
import com.maxim.webjs.service.Settings;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.sql.Date;

/**
 * Created by Максим on 30.08.2016.
 */
public class FriendDbStorage implements Storage<Friend> {
    private static final FriendDbStorage INSTANCE = new FriendDbStorage();
    public static FriendDbStorage getInstance() {
        return INSTANCE;
    }

    private final Connection connection;

    public FriendDbStorage () {
        final Settings settings = Settings.getInstance();
        try {
            this.connection = DriverManager.getConnection(settings.value("jdbc.url"), settings.value("jdbc.username"), settings.value("jdbc.password"));
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
    public Collection<Friend> values(){
        final List<Friend> friends = new ArrayList<Friend>();
        try (final Statement statement = this.connection.createStatement();
             final ResultSet rs = statement.executeQuery("select * from friend")) {
            while (rs.next()) {
                friends.add(new Friend(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDate("birthday"),
                        rs.getString("interests"),
                        rs.getInt("iduser")));
            }
        } catch (SQLException e) {
            new RuntimeException("Could not get all friends",e);
        }
        return friends;}

    public int add(final Friend friend){
        try (final PreparedStatement statement =
                     this.connection.prepareStatement("insert into friend (name,birthday,interests,iduser) values (?,?,?,?)",
                             Statement.RETURN_GENERATED_KEYS)) {

        statement.setString(1,friend.getName());
        statement.setDate(2,new Date(friend.getBirthday().getTime()));
        statement.setString(3,friend.getInterests());
        statement.setInt(4,friend.getIdUser());
        statement.executeUpdate();
        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            }
        }
        } catch (SQLException e) {
            new RuntimeException("Could not create new user",e);
        }
        return -1;
    }

    public void edit(final Friend friend){
        try (final PreparedStatement statement = this.connection.prepareStatement("update friend set name=?,birthday=?,interests=? where id=?",
                Statement.RETURN_GENERATED_KEYS)) {

            statement.setInt(4,friend.getId());
            statement.setString(1,friend.getName());
            statement.setDate(2,new Date(friend.getBirthday().getTime()));
            statement.setString(3,friend.getInterests());
            statement.executeUpdate();
        } catch (SQLException e) {
            new RuntimeException("Could not change friend",e);
        }
    }

    public void delete(final int id){
        try (final PreparedStatement statement = this.connection.prepareStatement("delete from friend where id=?",
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            new RuntimeException("Could not delete friend",e);
        }
    };

    public Friend get(final int id){
        Friend friend=null;
        try (final PreparedStatement statement = this.connection.prepareStatement("select * from friend where id=?",
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1,id);
            final ResultSet rs=statement.executeQuery();
            friend=new Friend(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getDate("birthday"),
                    rs.getString("interests"),
                    rs.getInt("iduser"));
        } catch (SQLException e) {
            new RuntimeException("Could not get friend",e);
        }
        return friend;
    }

    public Collection<Friend> get(Map<String,Object> params){
        if(params==null || params.isEmpty())
            throw new IllegalArgumentException("Params is empty");
        //get id user
        int idUser=-1;
        try {
            idUser = (Integer) params.get("iduser");
        }catch(ClassCastException|NullPointerException e){
            new RuntimeException("Params don't contain the key'iduser'",e);
        }

        //get friends
        final List<Friend> friends = new ArrayList<Friend>();
        try (final PreparedStatement statement = this.connection.prepareStatement("select * from friend where iduser=?")) {
            statement.setInt(1,idUser);
            final ResultSet rs=statement.executeQuery();
            while (rs.next()) {
                friends.add(new Friend(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDate("birthday"),
                        rs.getString("interests"),
                        rs.getInt("iduser")));
            }
        } catch (SQLException e) {
            new RuntimeException("Could not get friends for this user",e);
        }
        return friends;};

    public void close(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    };
}
