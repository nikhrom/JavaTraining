package com.github.nikhrom.javatraining.http.servlet;


import com.github.nikhrom.javatraining.http.dto.UserDto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/sessions")
public class SessionServlet extends HttpServlet {

    private static final String USER = "user";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var session = req.getSession();
        var user = (UserDto) session.getAttribute(USER);
        if(user == null){
            user = UserDto.builder()
                    .id(25L)
                    .mail("test@gmail.com").build();

            session.setAttribute(USER, user);
        }
    }

}
