package com.github.nikhrom.javatraining.http.practice.entity;

import java.util.Objects;

public class Ticket {

    private Integer id;
    private Integer passengerNo;
    private String passengerName;
    private Integer flightId;
    private String cost;
    private String seatNo;

    public Ticket() {
    }

    public Ticket(Integer id, Integer passengerNo, String passengerName,
                  Integer flightId, String cost, String seatNo) {
        this.id = id;
        this.passengerNo = passengerNo;
        this.passengerName = passengerName;
        this.flightId = flightId;
        this.cost = cost;
        this.seatNo = seatNo;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", passengerNo=" + passengerNo +
                ", passengerName='" + passengerName + '\'' +
                ", flightId=" + flightId +
                ", cost='" + cost + '\'' +
                ", seatNo='" + seatNo + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(id, ticket.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPassengerNo() {
        return passengerNo;
    }

    public void setPassengerNo(Integer passengerNo) {
        this.passengerNo = passengerNo;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public Integer getFlightId() {
        return flightId;
    }

    public void setFlightId(Integer flightId) {
        this.flightId = flightId;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(String seatNo) {
        this.seatNo = seatNo;
    }

}
