package com.github.nikhrom.javatraining.http.practice.servlet;


import com.github.nikhrom.javatraining.http.practice.dto.TicketFilter;
import com.github.nikhrom.javatraining.http.practice.service.TicketService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@WebServlet("/tickets")
public class TicketServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        var flightId = Integer.parseInt(req.getParameter("flightId"));

        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

        try (PrintWriter writer = resp.getWriter()) {
            writer.write("<h1> Пассажиры перелёта №%s: </h1>".formatted(flightId));

            writer.write("<ul>");

            TicketService.getInstance()
                    .findAll(TicketFilter.newBuilder().flightId(flightId).limit(10).build())
                    .forEach(ticketDto -> {
                        writer.write("""
                                <li> %s </li>
                                """.formatted(ticketDto.getDescription()));
                    });

            writer.write("</ul>");
        }

    }
}
