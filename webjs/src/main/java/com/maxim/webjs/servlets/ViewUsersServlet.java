package com.maxim.webjs.servlets;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.maxim.manager.User;
import com.maxim.webjs.service.AppSpringContext;
import com.maxim.webjs.storage.Storage;
import com.maxim.webjs.storage.Storages;
import com.maxim.webjs.forms.UserForm;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.JsonNode;

/**
 * Created by Максим on 24.08.2016.
 */
public class ViewUsersServlet extends HttpServlet {
    private static Storage<User> USER_STORAGE =
            AppSpringContext.getInstance().getBean(Storages.class).getUsersStorage();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.addHeader("Content-Type", "application/json; charset=utf-8");
        ServletOutputStream out = resp.getOutputStream();
        out.print(new ObjectMapper().writeValueAsString(USER_STORAGE.values()));
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Content-Type", "application/json; charset=utf-8");
        int type= Integer.decode(req.getParameter("type"));
        switch(type) {
            case 0:
                final UserForm form = new ObjectMapper().readValue(req.getInputStream(), UserForm.class);
                USER_STORAGE.add(new User(form.getName(), form.getNumber(), form.getEmail()));
                resp.getOutputStream().write("{'result' : 'true'}".getBytes());
                break;
            case 1:
                JsonNode rootNode=new ObjectMapper().readTree(req.getInputStream());
                int idUserForDelete=rootNode.path("userid").getIntValue();
                USER_STORAGE.delete(idUserForDelete);
                resp.getOutputStream().write(("{'result' : true"+"}").getBytes());
                break;
        }
    }
}
