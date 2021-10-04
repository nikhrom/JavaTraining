package com.github.nikhrom.javatraining.http.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

@WebServlet("/first")
public class FirstServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var headerNames = req.getHeaderNames();
        while(headerNames.hasMoreElements()){
            var header = headerNames.nextElement();
            System.out.println(req.getHeader(header));
        }

        var param = req.getParameter("param");
        var ids = req.getParameterValues("id");

        System.out.println("param = " + param);
        System.out.println("id = " + Arrays.toString(ids));


        resp.setContentType("text/html; charset=UTF-8");
        resp.setHeader("example", "hello");

        try (var writer = resp.getWriter()) {
            writer.write("<h1>Hello from FirstServlet's method GET</h1>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println(readBody(req));

        var message = "<h1>Hello from FirstServlet's method POST</h1>";
        answerTheRequest(resp, message);
    }

    private void answerTheRequest(HttpServletResponse resp, String message) throws IOException {
        resp.setContentType("text/html; charset=UTF-8");
        resp.setHeader("example", "hello");

        try (var writer = resp.getWriter()) {
            writer.write(message);
        }
    };

    private String readBody(HttpServletRequest request) throws IOException {
        try (var reader = request.getReader();
             var lines = reader.lines()) {

            return lines.collect(Collectors.joining(", ", "[", "]"));
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
