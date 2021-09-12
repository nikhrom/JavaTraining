package com.github.nikhrom.javatraining.jdbc.starter;

import com.github.nikhrom.javatraining.jdbc.starter.dao.TicketDao;
import com.github.nikhrom.javatraining.jdbc.starter.dto.TicketFilter;
import com.github.nikhrom.javatraining.jdbc.starter.entity.Ticket;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class DaoRunner {

    public static void main(String[] args) {
        var ticketFilter = new TicketFilter(10, 0, "A1", "Павел Назаренко");
        var all = TicketDao.getInstance().findAll(ticketFilter);
        all.forEach(System.out::println);
    }

    private static void findAllTest() {
        var all = TicketDao.getInstance().findAll();
        all.forEach(System.out::println);
    }

    private static void updateTest() {
        var ticketDao = TicketDao.getInstance();
        var ticket = ticketDao.findById(2);
        ticket.ifPresent(System.out::println);

        ticket.ifPresent(ticket1 -> {
            ticket1.setCost(BigDecimal.valueOf(2200.00));
            ticketDao.update(ticket1);
        });

        ticket.ifPresent(System.out::println);
    }

    private static void deleteTest() {
        var ticketDao = TicketDao.getInstance();
        var delete = ticketDao.delete(20);
        System.out.println(delete);
    }

    private static void saveTest() {
        var ticketDao = TicketDao.getInstance();
        var ticket = new Ticket();

        ticket.setPassengerNo(4321);
        ticket.setPassengerName("Test");
        ticket.setFlightId(3);
        ticket.setSeatNo("A2");
        ticket.setCost(BigDecimal.valueOf(2000));

        var savedTicket = ticketDao.save(ticket);

        System.out.println(savedTicket);
    }

}
