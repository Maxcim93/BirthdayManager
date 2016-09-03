package com.maxim.webjs.servlets;

import com.maxim.manager.Friend;
import com.maxim.webjs.storage.FriendDbStorage;
import com.maxim.webjs.forms.FriendForm;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Максим on 25.08.2016.
 */
public class ViewFriendsServlet extends HttpServlet {
    private static FriendDbStorage FRIEND_STORAGE =FriendDbStorage.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.addHeader("Content-Type", "application/json; charset=utf-8");
        ServletOutputStream out = resp.getOutputStream();

        ObjectMapper mapper=new ObjectMapper();
        SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd");
        mapper.setDateFormat(format);
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("iduser",Integer.decode(req.getParameter("id")));
        out.print(mapper.writeValueAsString(FRIEND_STORAGE.get(params)));
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
                        Integer.decode(req.getParameter("id"))));
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
