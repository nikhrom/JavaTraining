package com.github.nikhrom.javatraining.http.servlet;

import com.github.nikhrom.javatraining.http.practice.dto.FlightDto;
import com.github.nikhrom.javatraining.http.practice.entity.Flight;
import com.github.nikhrom.javatraining.http.practice.service.FlightService;
import com.github.nikhrom.javatraining.http.util.JspHelper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@WebServlet("/content")
public class ContentServlet extends HttpServlet{

    private final FlightService flightService = FlightService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<FlightDto> flightDtos = flightService.findAll();
        req.setAttribute("flights", flightDtos);

        req.getSession().setAttribute("flightsMap", flightDtos.stream()
                .collect(toMap(flightDto -> String.valueOf(flightDto.getId()), FlightDto::getDescription)));


        req.getRequestDispatcher(JspHelper.getPath("content"))
                .forward(req, resp);
    }
}
