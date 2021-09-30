package com.github.nikhrom.javatraining.http.practice.service;

import com.github.nikhrom.javatraining.http.practice.dao.TicketDao;
import com.github.nikhrom.javatraining.http.practice.dto.TicketDto;
import com.github.nikhrom.javatraining.http.practice.dto.TicketFilter;
import com.github.nikhrom.javatraining.http.practice.entity.Ticket;

import java.util.List;


import static java.util.stream.Collectors.*;

public class TicketService {
    private static final TicketService INSTANCE = new TicketService();

    private TicketService(){}

    public List<TicketDto> findAll(TicketFilter filter){
        return TicketDao.getInstance()
                .findAll(filter)
                .stream()
                .map(ticket ->
                    new TicketDto(
                            ticket.getId(),
                            "%s - %s - %s".formatted(
                                    ticket.getSeatNo(), ticket.getPassengerName(), ticket.getCost()
                            )
                    ))
                .collect(toList());
    }

    public static TicketService getInstance() {
        return INSTANCE;
    }
}
