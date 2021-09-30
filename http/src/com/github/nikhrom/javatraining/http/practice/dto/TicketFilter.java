package com.github.nikhrom.javatraining.http.practice.dto;

public record TicketFilter(int flightId,
                           int limit,
                           int offset,
                           String seatNo,
                           String passengerName) {


    public static TicketFilter createEmpty(){
        return new TicketFilterBuilder().build();
    }

    public static TicketFilterBuilder newBuilder(){
        return new TicketFilterBuilder();
    }
}
