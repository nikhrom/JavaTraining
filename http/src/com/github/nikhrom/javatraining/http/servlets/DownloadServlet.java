package com.github.nikhrom.javatraining.http.servlets;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
