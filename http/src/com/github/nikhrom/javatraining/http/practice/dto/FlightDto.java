package com.github.nikhrom.javatraining.http.practice.dto;

import lombok.*;

@Value
@Builder
public class FlightDto {
    Integer id;
    String description;
}
