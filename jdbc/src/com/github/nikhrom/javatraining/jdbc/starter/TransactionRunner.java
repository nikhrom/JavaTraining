package com.github.nikhrom.javatraining.jdbc.starter;

import com.github.nikhrom.javatraining.jdbc.starter.util.ConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionRunner {

    public static void main(String[] args) {
        int flightId = 9;
        var deleteFlightSql = "DELETE FROM flight WHERE id = ?;";
        var deleteTicketSql = "DELETE FROM ticket WHERE flight_id = ?;";
        try (var connection = ConnectionManager.open();
            var flightStatement  = connection.prepareStatement(deleteFlightSql);
            var ticketStatement = connection.prepareStatement(deleteTicketSql)) {

            ticketStatement.setInt(1, flightId);
            flightStatement.setInt(1, flightId);


            ticketStatement.executeUpdate();
            flightStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
