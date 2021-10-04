package com.github.nikhrom.javatraining.http.servlet;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/download")
public class DownloadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Content-Disposition", "attachment; filename=\"info.json\"");
        resp.setContentType("application/json");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.toString());


        try (var outputStream = resp.getOutputStream();
            var stream = DownloadServlet.class.getClassLoader().getResourceAsStream("first.json")) {

            outputStream.write(stream.readAllBytes());
        }
    }
}
