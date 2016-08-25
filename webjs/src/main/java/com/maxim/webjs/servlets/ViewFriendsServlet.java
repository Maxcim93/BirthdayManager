package com.maxim.webjs.servlets;

import com.maxim.webjs.storage.UsersCache;
import org.codehaus.jackson.map.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        out.print(new ObjectMapper().writeValueAsString(USER_CACHE.get(Integer.decode(req.getParameter("id"))).getFriends()));
        out.flush();
    }
}
