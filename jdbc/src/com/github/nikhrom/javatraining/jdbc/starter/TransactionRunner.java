package com.github.nikhrom.javatraining.jdbc.starter;

import com.github.nikhrom.javatraining.jdbc.starter.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class TransactionRunner {

    public static void main(String[] args) throws SQLException {
        int flightId = 7;
        var deleteFlightSql = "DELETE FROM flight WHERE id = %d;".formatted(flightId);
        var deleteTicketSql = "DELETE FROM ticket WHERE flight_id = %d;".formatted(flightId);

        Connection connection = null;
        Statement statement = null;

        try {
            connection = ConnectionManager.get();
            connection.setAutoCommit(false);

            statement = connection.createStatement();
            statement.addBatch(deleteTicketSql);
            statement.addBatch(deleteFlightSql);
            statement.executeBatch();

            connection.commit();
        } catch (SQLException e) {
            if (connection != null)
                connection.rollback();

            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null)
                connection.close();

            if (statement != null)
                statement.close();
        }
    }

    private static void runTransaction() throws SQLException {
        int flightId = 9;
        var deleteFlightSql = "DELETE FROM flight WHERE id = ?;";
        var deleteTicketSql = "DELETE FROM ticket WHERE flight_id = ?;";

        Connection connection = null;
        PreparedStatement flightStatement = null;
        PreparedStatement ticketStatement = null;

        try{
            connection = ConnectionManager.get();
            flightStatement  = connection.prepareStatement(deleteFlightSql);
            ticketStatement = connection.prepareStatement(deleteTicketSql);

            connection.setAutoCommit(false);

            ticketStatement.setInt(1, flightId);
            flightStatement.setInt(1, flightId);


            ticketStatement.executeUpdate();

            if(true)
                throw new RuntimeException("ouch");

            flightStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            if(connection != null)
                connection.rollback();

            throw e;
        }finally {
            if(connection != null)
                connection.close();

            if(flightStatement != null)
                flightStatement.close();

            if(ticketStatement != null)
                ticketStatement.close();
        }
    }

}
