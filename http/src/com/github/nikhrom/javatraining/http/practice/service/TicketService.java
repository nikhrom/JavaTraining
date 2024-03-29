package com.github.nikhrom.javatraining.http.practice.service;

import com.github.nikhrom.javatraining.http.practice.dao.TicketDao;
import com.github.nikhrom.javatraining.http.practice.dto.TicketDto;
import com.github.nikhrom.javatraining.http.practice.dto.TicketFilter;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class TicketService {
    private static final TicketService INSTANCE = new TicketService();

    private TicketService(){}

    public List<TicketDto> findTicketsByFlightId(int flightId){
        TicketFilter ticketFilter = TicketFilter.builder()
                .flightId(flightId).build();

        return findAll(ticketFilter);
    }

    public List<TicketDto> findAll(TicketFilter filter){
        return TicketDao.getInstance()
                .findAll(filter)
                .stream()
                .map(ticket -> TicketDto.builder()
                                .id(ticket.getId())
                                .description("%s - %s - %s".formatted(
                                        ticket.getSeatNo(), ticket.getPassengerName(), ticket.getCost()
                                )).build()
                )
                .collect(toList());
    }

    public static TicketService getInstance() {
        return INSTANCE;
    }
}
