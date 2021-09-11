package com.github.nikhrom.javatraining.jdbc.starter;

import com.github.nikhrom.javatraining.jdbc.starter.util.ConnectionManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.sql.*;

public class BlobRunner {

    public static void main(String[] args) throws IOException, SQLException {
        //saveImage();
        getImage();
    }


    private static void getImage() throws SQLException, IOException {
        String sql = """
                SELECT image
                FROM aircraft
                WHERE id = ?;
                """;

        try(var connection = ConnectionManager.get();
            var preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setInt(1, 1);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                byte[] image = resultSet.getBytes("image");
                Files.write(Path.of("resources", "airbus220_new.jpg"), image, StandardOpenOption.CREATE);
            }
        }

    }

    private static void saveImage() throws SQLException, IOException {

        var sql = """
                UPDATE aircraft
                SET image = ?
                WHERE id = 1;
                """;

        try (Connection connection = ConnectionManager.get();
            var preparedStatement = connection.prepareStatement(sql)) {


            preparedStatement.setBytes(1, Files.readAllBytes(Path.of("resources", "airbus220.jpg")));
            preparedStatement.executeUpdate();
        }
    }


}
