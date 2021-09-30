package com.github.nikhrom.javatraining.http.practice.dto;

import java.util.Objects;

public class TicketDto {

    private final Integer id;
    private final String description;


    public TicketDto(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    @Override
    public String toString() {
        return "TicketDto{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketDto ticketDto = (TicketDto) o;
        return id.equals(ticketDto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}
