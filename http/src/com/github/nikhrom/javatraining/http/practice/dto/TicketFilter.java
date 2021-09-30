package com.github.nikhrom.javatraining.http.practice.dto;


import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class TicketFilter {
    int flightId;
    int limit;
    int offset;
    String seatNo;
    String passengerName;
}
