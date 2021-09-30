package com.github.nikhrom.javatraining.http.practice.dto;

import lombok.Builder;
import lombok.Value;

import java.util.Objects;

@Value
@Builder
public class TicketDto {
    Integer id;
    String description;
}
