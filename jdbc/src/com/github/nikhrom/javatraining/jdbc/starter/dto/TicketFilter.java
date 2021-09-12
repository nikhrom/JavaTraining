package com.github.nikhrom.javatraining.jdbc.starter.dto;

public record TicketFilter(int limit,
                           int offset,
                           String seatNo,
                           String passengerName) {
}
