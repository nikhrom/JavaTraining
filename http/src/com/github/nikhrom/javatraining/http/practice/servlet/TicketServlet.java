package com.github.nikhrom.javatraining.http.practice.servlet;


import com.github.nikhrom.javatraining.http.practice.service.TicketService;
import com.github.nikhrom.javatraining.http.util.JspHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/tickets")
public class TicketServlet extends HttpServlet {

    private final TicketService ticketService = TicketService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var flightId = Integer.parseInt(req.getParameter("flightId"));
        req.setAttribute("tickets", ticketService.findTicketsByFlightId(flightId));

        req.getRequestDispatcher(JspHelper.getPath("tickets"))
            .forward(req, resp);
    }
}
