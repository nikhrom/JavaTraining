package com.github.nikhrom.javatraining.http.practice.service;

import com.github.nikhrom.javatraining.http.practice.dao.FlightDao;
import com.github.nikhrom.javatraining.http.practice.dto.FlightDto;
import com.github.nikhrom.javatraining.http.practice.entity.Flight;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class FlightService {

    private static final FlightService INSTANCE = new FlightService();

    private final FlightDao flightDao = FlightDao.getInstance();

    private FlightService(){};

    public List<FlightDto> findAll() {
        return flightDao.findAll().stream()
                .map(flight -> FlightDto.builder()
                        .id(flight.getId())
                        .description("%s - %s - %s".formatted(
                                flight.getDepartureAirportCode(),
                                flight.getArrivalAirportCode(),
                                flight.getStatus()
                        )).build()
                )
                .collect(toList());
    }

    public static FlightService getInstance() {
        return INSTANCE;
    }
}
