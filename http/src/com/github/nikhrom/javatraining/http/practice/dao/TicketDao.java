package com.github.nikhrom.javatraining.http.practice.dao;

import com.github.nikhrom.javatraining.http.practice.dto.TicketFilter;
import com.github.nikhrom.javatraining.http.practice.entity.Ticket;
import com.github.nikhrom.javatraining.http.practice.util.ConnectionManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TicketDao implements Dao<Integer, Ticket> {

    private static final TicketDao INSTANCE = new TicketDao();

    private static final String FIND_ALL_SQL = """
            SELECT id,
                passenger_no,
                passenger_name,
                flight_id,
                seat_no,
                cost
            FROM ticket
            """;

    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + """
            WHERE id = ?;
            """;

    public List<Ticket> findAll(TicketFilter filter){

        List<Object> parameters = new ArrayList<>();
        List<String> wherePatterns = new ArrayList<>();

        if(filter.getSeatNo() != null){
            wherePatterns.add("seat_no LIKE ?");
            parameters.add("%" + filter.getSeatNo() + "%");
        }

        if(filter.getPassengerName() != null){
            wherePatterns.add("passenger_name = ?");
            parameters.add(filter.getPassengerName());
        }

        if(filter.getFlightId() != 0){
            wherePatterns.add("flight_id = ?");
            parameters.add(filter.getFlightId());
        }

        StringBuilder where = new StringBuilder();

        where.append(String.join(" AND ", wherePatterns));

        if(filter.getLimit() != 0) {
            parameters.add(filter.getLimit());
            where.append(" LIMIT ? ");
        }

        if(filter.getOffset() != 0) {
            parameters.add(filter.getOffset());
            where.append(" OFFSET ? ");
        }

        var sql = where.length() > 0 ? FIND_ALL_SQL + " WHERE " + where.toString():
                                        FIND_ALL_SQL;



        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(sql)) {

            for (int i = 0 ; i < parameters.size(); i++){
                preparedStatement.setObject(i + 1, parameters.get(i));
            }

            System.out.println(preparedStatement.toString());
            var resultSet = preparedStatement.executeQuery();
            var tickets = new ArrayList<Ticket>();
            while(resultSet.next()){
                tickets.add(buildTicket(resultSet));
            }

            return tickets;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Ticket> findAll() {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {

            var resultSet = preparedStatement.executeQuery();
            List<Ticket> tickets = new ArrayList<>();

            while (resultSet.next()){
                tickets.add(buildTicket(resultSet));
            }

            return tickets;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    private Ticket buildTicket(ResultSet resultSet) throws SQLException {
        return new Ticket(
                resultSet.getInt("id"),
                resultSet.getInt("passenger_no"),
                resultSet.getString("passenger_name"),
                resultSet.getInt("flight_id"),
                resultSet.getString("seat_no"),
                resultSet.getString("cost")
        );
    }



    @Override
    public Ticket save(Ticket entity) {
        return null;
    }

    @Override
    public Optional<Ticket> findById(Integer id) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {

            preparedStatement.setInt(1, id);
            var resultSet = preparedStatement.executeQuery();

            Ticket ticket = null;
            if (resultSet.next()) {
                ticket = buildTicket(resultSet);
            }

            return Optional.ofNullable(ticket);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Ticket entity) {

    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    public static TicketDao getInstance() {
        return INSTANCE;
    }
}
