package com.github.nikhrom.javatraining.http.practice.dao;

import com.github.nikhrom.javatraining.http.practice.entity.Flight;
import com.github.nikhrom.javatraining.http.practice.entity.FlightStatus;
import com.github.nikhrom.javatraining.http.practice.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FlightDao implements Dao<Integer, Flight>{

    private static final FlightDao INSTANCE = new FlightDao();

    private static final String FIND_ALL = """
            SELECT id, 
                flight_no, 
                departure_date, 
                departure_airport_code,
                arrival_date,
                arrival_airport_code,
                aircraft_id,
                status
            FROM flight
            """;

    private static final String FIND_BY_ID = FIND_ALL +
             " WHERE id = ?";



    private FlightDao(){};


    @Override
    public List<Flight> findAll() {



        try (var connection = ConnectionManager.get();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL)) {

            List<Flight> allFlights = new ArrayList<>();
            var resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                var flight = buildFlight(resultSet);
                allFlights.add(flight);
            }

            return allFlights;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


    @Override
    public Flight save(Flight entity) {
        return null;
    }

    @Override
    public Optional<Flight> findById(Integer id) {
        Optional<Flight> flightOptional = Optional.empty();

        try (var connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {

            preparedStatement.setInt(1, id);
            var resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                var flight = buildFlight(resultSet);

                flightOptional = Optional.of(flight);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


        return flightOptional;
    }

    @Override
    public void update(Flight entity) {

    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    private Flight buildFlight(ResultSet resultSet) throws SQLException {

        return new Flight(resultSet.getInt("id"),
                resultSet.getString("flight_no"),
                resultSet.getTimestamp("departure_date").toLocalDateTime(),
                resultSet.getString("departure_airport_code"),
                resultSet.getTimestamp("arrival_date").toLocalDateTime(),
                resultSet.getString("arrival_airport_code"),
                resultSet.getInt("aircraft_id"),
                FlightStatus.valueOf(resultSet.getString("status").toUpperCase())
        );
    }

    public static FlightDao getInstance() {
        return INSTANCE;
    }
}
