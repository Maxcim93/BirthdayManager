package com.maxim.webjs.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.maxim.manager.User;
import com.maxim.webjs.storage.UserForm;
import org.codehaus.jackson.map.ObjectMapper;
import com.maxim.webjs.storage.UsersCache;

/**
 * Created by Максим on 24.08.2016.
 */
public class ViewUsersServlet extends HttpServlet {
    private static UsersCache USER_CACHE =UsersCache.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.addHeader("Content-Type", "application/json; charset=utf-8");
        ServletOutputStream out = resp.getOutputStream();
        out.print(new ObjectMapper().writeValueAsString(USER_CACHE.values()));
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Content-Type", "application/json; charset=utf-8");
        final UserForm form = new ObjectMapper().readValue(req.getInputStream(), UserForm.class);
        USER_CACHE.add(new User(form.getName(), form.getNumber(), form.getEmail()));
        resp.getOutputStream().write("{'result' : 'true'}".getBytes());
    }
}
