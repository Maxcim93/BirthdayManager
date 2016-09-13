package com.maxim.webjs.servlets;

import com.maxim.manager.Friend;
import com.maxim.manager.User;
import com.maxim.webjs.service.AppSpringContext;
import com.maxim.webjs.forms.FriendForm;
import com.maxim.webjs.storage.Storage;
import com.maxim.webjs.storage.Storages;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Максим on 25.08.2016.
 */
public class ViewFriendsServlet extends HttpServlet {
    private static Storage<Friend> FRIEND_STORAGE =
            AppSpringContext.getInstance().getBean(Storages.class).getFriendsStorage();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.addHeader("Content-Type", "application/json; charset=utf-8");
        ServletOutputStream out = resp.getOutputStream();

        ObjectMapper mapper=new ObjectMapper();
        SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd");
        mapper.setDateFormat(format);
        //mapper.setVisibility(JsonMethod.FIELD, JsonAutoDetect.Visibility.ANY);
        //mapper.enableDefaultTypingAsProperty(ObjectMapper.DefaultTyping.OBJECT_AND_NON_CONCRETE, "type");

        Map<String,Object> params=new HashMap<String,Object>();
        params.put("iduser",Integer.decode(req.getParameter("id")));
        Collection<Friend> friendsForUser=FRIEND_STORAGE.get(params);
        out.print(mapper.writeValueAsString(friendsForUser));
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
                FRIEND_STORAGE.add(new Friend(
                        form.getName(),
                        form.getBirthday(),
                        form.getInterests(),
                        new User(Integer.decode(req.getParameter("id")),null,null,null)));
                resp.getOutputStream().write("{'result' : 'true'}".getBytes());
                break;
            case 1:
                JsonNode rootNode=new ObjectMapper().readTree(req.getInputStream());
                int idFriendForDelete=rootNode.path("friendid").getIntValue();
                FRIEND_STORAGE.delete(idFriendForDelete);
                resp.getOutputStream().write(("{'result' : 'true'}").getBytes());
                break;

        }
    }
}
