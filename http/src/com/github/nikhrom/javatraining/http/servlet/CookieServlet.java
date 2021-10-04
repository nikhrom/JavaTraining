package com.github.nikhrom.javatraining.http.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

@WebServlet("/cookies")
public class CookieServlet extends HttpServlet {

    private static final String UNIQUE_ID = "userId";
    private static final AtomicInteger counter = new AtomicInteger();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var cookies = req.getCookies();
        if (cookies == null || Arrays.stream(cookies)
                .filter(cookie -> UNIQUE_ID.equals(cookie.getName()))
                .findFirst().isEmpty()){

            var cookie = new Cookie(UNIQUE_ID, "1");
            cookie.setPath("/cookies");
            cookie.setMaxAge(15 * 60);
            resp.addCookie(cookie);
            counter.incrementAndGet();
        }

        resp.setContentType("text/html");

        try (var writer = resp.getWriter()) {
            writer.write(String.valueOf(counter.get()));
        }
    }
}
