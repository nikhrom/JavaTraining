package com.github.nikhrom.javatraining.http.practice.dao;

import com.github.nikhrom.javatraining.http.practice.dto.UserFilter;
import com.github.nikhrom.javatraining.http.practice.entity.Gender;
import com.github.nikhrom.javatraining.http.practice.entity.User;
import com.github.nikhrom.javatraining.http.practice.entity.UserRole;
import com.github.nikhrom.javatraining.http.practice.util.ConnectionManager;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDao implements Dao<Integer, User>{

    static final UserDao INSTANCE = new UserDao();

    static final String SAVE_SQL = """
            INSERT INTO users(name, birthday, email, password, role, gender, image) 
            VALUES (?, ?, ?, ?, ?, ?, ?)           
            """;

    static final String FIND_ALL_SQL = """
            SELECT id, name, birthday, email, password, role, gender, image
            FROM users
            """;


    static final String HAS_EMAIL_SQL = """
            SELECT exists(SELECT 1 FROM users WHERE email LIKE ?)
            """;


    @SneakyThrows
    public boolean hasEmail(String email){
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(HAS_EMAIL_SQL)) {

            preparedStatement.setString(1, email);

            var resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getBoolean(1);
        }
    }

    @SneakyThrows
    public List<User> findAll(UserFilter filter){
        List<String> wherePatterns = new ArrayList<>();
        List<Object> parameters = new ArrayList<>();

        if(filter.getEmail() != null){
            wherePatterns.add(" email LIKE ? ");
            parameters.add(filter.getEmail());
        }

        var where = new StringBuilder();
        where.append(String.join(" AND ", wherePatterns));

        var sql = wherePatterns.isEmpty()? FIND_ALL_SQL:
                  FIND_ALL_SQL + " WHERE " +  where.toString();


        List<User> users = new ArrayList<>();

        try (var connection = ConnectionManager.get();
            var preparedStatement = connection.prepareStatement(sql)) {

            for(int i = 0; i < parameters.size(); i++){
                preparedStatement.setObject(i + 1, parameters.get(i));
            }

            var resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                users.add(buildUser(resultSet));
            }

            return users;
        }

    }

    @SneakyThrows
    private User buildUser(ResultSet resultSet) {
        User user = User.builder()
                .id(resultSet.getInt("id"))
                .password(resultSet.getString("password"))
                .role(UserRole.valueOf(resultSet.getString("role")))
                .gender(Gender.valueOf(resultSet.getString("gender")))
                .email(resultSet.getString("email"))
                .name(resultSet.getString("name"))
                .birthday(resultSet.getDate("birthday").toLocalDate())
                .image(Optional.ofNullable(resultSet.getString("image")))
                .build();

        return user;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @SneakyThrows
    @Override
    public User save(User entity) {
        try (var connection = ConnectionManager.get();
            var preparedStatement = connection.prepareStatement(SAVE_SQL, RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, entity.getName());
            preparedStatement.setDate(2, Date.valueOf(entity.getBirthday()));
            preparedStatement.setString(3, entity.getEmail());
            preparedStatement.setString(4, entity.getPassword());
            preparedStatement.setString(5, entity.getRole().name());
            preparedStatement.setString(6, entity.getGender().name());
            preparedStatement.setString(7, entity.getImage().orElse(null));

            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            entity.setId(generatedKeys.getInt("id"));
            return entity;
        }
    }

    @Override
    public Optional<User> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    public static UserDao getInstance() {
        return INSTANCE;
    }
}
