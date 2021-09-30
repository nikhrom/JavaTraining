package com.github.nikhrom.javatraining.http.practice.dto;

public class TicketFilterBuilder {

    private int flightId;
    private int limit;
    private int offset;
    private String seatNo;
    private String passengerName;

    public TicketFilterBuilder() {
        reset();
    }

    public void reset(){
        flightId = 0;
        limit = 0;
        offset = 0;
        seatNo = null;
        passengerName = null;
    }


    public TicketFilter build(){
        return new TicketFilter(flightId, limit, offset, seatNo, passengerName);
    }

    public TicketFilterBuilder flightId(int flightId) {
        this.flightId = flightId;
        return this;
    }

    public TicketFilterBuilder limit(int limit) {
        this.limit = limit;
        return this;
    }

    public TicketFilterBuilder offset(int offset) {
        this.offset = offset;
        return this;
    }

    public TicketFilterBuilder seatNo(String seatNo) {
        this.seatNo = seatNo;
        return this;
    }

    public TicketFilterBuilder passengerName(String passengerName) {
        this.passengerName = passengerName;
        return this;
    }



}
