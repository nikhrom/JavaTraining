package com.github.nikhrom.javatraining.http.practice.servlet;

import com.github.nikhrom.javatraining.http.practice.dto.UserDto;
import com.github.nikhrom.javatraining.http.practice.service.UserService;
import com.github.nikhrom.javatraining.http.util.JspHelper;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final LoginServlet INSTANCE = new LoginServlet();

    private final UserService userService = UserService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspHelper.getPath("login"))
            .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var login = userService.login(
                req.getParameter("email"),
                req.getParameter("password")
        );

        login.ifPresentOrElse(
                user -> onLoginSuccess(req, resp, user),
                () -> onLoginFail(req, resp)
        );
    }

    @SneakyThrows
    private void onLoginFail(HttpServletRequest req, HttpServletResponse resp) {
        resp.sendRedirect("/login?error&email=" + req.getParameter("email"));
    }

    @SneakyThrows
    private void onLoginSuccess(HttpServletRequest req, HttpServletResponse resp, UserDto user){
        req.getSession().setAttribute("user", user);
        resp.sendRedirect("flights");
    }

    public static LoginServlet getInstance() {
        return INSTANCE;
    }
}
