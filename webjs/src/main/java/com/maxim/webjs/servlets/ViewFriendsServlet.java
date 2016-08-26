package com.maxim.webjs.servlets;

import com.maxim.manager.Friend;
import com.maxim.manager.User;
import com.maxim.webjs.storage.FriendForm;
import com.maxim.webjs.storage.UserForm;
import com.maxim.webjs.storage.UsersCache;
import org.codehaus.jackson.map.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by Максим on 25.08.2016.
 */
public class ViewFriendsServlet extends HttpServlet {
    private static UsersCache USER_CACHE =UsersCache.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.addHeader("Content-Type", "application/json; charset=utf-8");
        ServletOutputStream out = resp.getOutputStream();

        ObjectMapper mapper=new ObjectMapper();
        SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd");
        mapper.setDateFormat(format);
        out.print(mapper.writeValueAsString(USER_CACHE.get(Integer.decode(req.getParameter("id"))).getFriends()));
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Content-Type", "application/json; charset=utf-8");
        int type= Integer.decode(req.getParameter("type"));
        switch(type) {
            case 0:
                ObjectMapper mapper = new ObjectMapper();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                mapper.setDateFormat(format);
                final FriendForm form = mapper.readValue(req.getInputStream(), FriendForm.class);
                User user = USER_CACHE.get(Integer.decode(req.getParameter("id")));
                user.addFriend(new Friend(form.getName(), form.getBirthday(), form.getInterests()));
                resp.getOutputStream().write("{'result' : 'true'}".getBytes());
                break;
            case 1:
                int idUser=Integer.decode(req.getParameter("idu"));
                int idFriendForDelete=Integer.decode(req.getParameter("idf"));
                boolean res=USER_CACHE.deleteFriend(idUser,idFriendForDelete);
                resp.getOutputStream().write(("{'result' :"+res+"}").getBytes());
                break;

        }
    }
}
