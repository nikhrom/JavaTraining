package com.github.nikhrom.javatraining.http.practice.servlet;

import com.github.nikhrom.javatraining.http.practice.dto.CreateUserDto;
import com.github.nikhrom.javatraining.http.practice.entity.User;
import com.github.nikhrom.javatraining.http.practice.entity.UserRole;
import com.github.nikhrom.javatraining.http.practice.service.UserService;
import com.github.nikhrom.javatraining.http.util.JspHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    private static final UserService USER_SERVICE = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("roles", List.of(UserRole.values()));
        req.setAttribute("genders", List.of("male", "female"));

        req.getRequestDispatcher(JspHelper.getPath("registration"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var name = req.getParameter("name");
        var userDto = CreateUserDto.builder()
                .name(req.getParameter("name"))
                .email(req.getParameter("email"))
                .birthday(req.getParameter("birthday"))
                .gender(req.getParameter("gender"))
                .role(req.getParameter("role"))
                .password(req.getParameter("password"))
                .build();

        boolean access = USER_SERVICE.saveUser(userDto);

        req.setAttribute("access", access);

        req.getRequestDispatcher("");
    }

}
