package com.github.nikhrom.javatraining.http.practice.servlet;

import com.github.nikhrom.javatraining.http.practice.service.FlightService;
import com.github.nikhrom.javatraining.http.util.JspHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/flights")
public class FlightServlet extends HttpServlet {

    private final FlightService service = FlightService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("flights", service.findAll());
        req.getRequestDispatcher(JspHelper.getPath("flights"))
                .forward(req, resp);



    }
}
