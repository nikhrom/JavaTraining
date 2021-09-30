package com.github.nikhrom.javatraining.http.practice.servlet;

import com.github.nikhrom.javatraining.http.practice.dto.FlightDto;
import com.github.nikhrom.javatraining.http.practice.service.FlightService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;


@WebServlet("/flights")
public class FlightServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());



        try (var writer = resp.getWriter()) {
            writer.write("<h1>Список перелётов:</h1>");
            writer.write("<ul>");
            FlightService.getInstance()
                    .findAll()
                    .forEach(flightDto -> {
                        writer.write("""
                                <li>
                                    <a href="/tickets?flightId=%d">%s</a>
                                </li>
                                """.formatted(flightDto.getId(), flightDto.getDescription())
                        );
                    });

            writer.write("</ul>");

        }
    }
}
