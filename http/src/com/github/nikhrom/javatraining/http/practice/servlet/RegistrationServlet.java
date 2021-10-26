package com.github.nikhrom.javatraining.http.practice.servlet;

import com.github.nikhrom.javatraining.http.practice.dto.CreateUserDto;
import com.github.nikhrom.javatraining.http.practice.entity.Gender;
import com.github.nikhrom.javatraining.http.practice.entity.UserRole;
import com.github.nikhrom.javatraining.http.practice.exception.ValidationException;
import com.github.nikhrom.javatraining.http.practice.service.UserService;
import com.github.nikhrom.javatraining.http.util.JspHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@MultipartConfig(fileSizeThreshold = 1024 * 1024)
@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    private static final RegistrationServlet INSTANCE = new RegistrationServlet();

    private final UserService userService = UserService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("roles", UserRole.values());
        req.setAttribute("genders", Gender.values());

        req.getRequestDispatcher(JspHelper.getPath("registration"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var userDto = CreateUserDto.builder()
                .name(req.getParameter("name"))
                .email(req.getParameter("email"))
                .birthday(req.getParameter("birthday"))
                .gender(req.getParameter("gender"))
                .role(req.getParameter("role"))
                .password(req.getParameter("password"))
                .image(req.getPart("image"))
                .build();

        try {
            userService.saveUser(userDto);
            resp.sendRedirect(JspHelper.getPath("login"));
        }catch (ValidationException exception){
            req.setAttribute("errors", exception.getErrors());
            doGet(req, resp);
        }


    }


    public static RegistrationServlet getInstance() {
        return INSTANCE;
    }
}
