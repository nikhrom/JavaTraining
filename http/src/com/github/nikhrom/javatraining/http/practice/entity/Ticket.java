package com.github.nikhrom.javatraining.http.practice.entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Ticket {
    private Integer id;
    private Integer passengerNo;
    private String passengerName;
    private Integer flightId;
    private String cost;
    private String seatNo;
}
