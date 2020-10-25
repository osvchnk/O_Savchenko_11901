package ru.itis.repositories;

import ru.itis.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UsersRepository{

    private static final String SQL_FIND_ALL_USERS = "select * from app_user";
    private static final String SQL_FIND_ALL_USERS_BY_AGE = "select * from app_user where age = ?";
    private Connection connection;
    private SimpleJdbcTemplate jdbcTemplate;

    private RowMapper<User> userRowMapper = row -> User.builder().
            id(row.getLong("id")).
            first_name(row.getString("first_name")).
            last_name(row.getString("last_name")).
            age(row.getInt("age")).
            login(row.getString("login")).
            password(row.getString("password")).
            build();

    public UsersRepositoryJdbcImpl(Connection connection){
        this.connection = connection;
        this.jdbcTemplate = new SimpleJdbcTemplate(connection);
    }

    @Override
    public List<User> findAllByAge(Integer age) {
//        try {
//            PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_USERS_BY_AGE);
//            statement.setInt(1, age);
//            ResultSet result = statement.executeQuery();
//
//            List<User> users = new ArrayList<>();
//
//            while (result.next()) {
//                users.add(userRowMapper.mapRow(result));
//            }
//            return users;
//        } catch (SQLException e) {
//            throw new IllegalStateException(e);
//        }
        return jdbcTemplate.queryForList(SQL_FIND_ALL_USERS_BY_AGE, userRowMapper, age);
    }

    @Override
    public boolean exist(String login, String password) {
        return false;
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
//        try {
//            Statement statement = connection.createStatement();
//            ResultSet result = statement.executeQuery(SQL_FIND_ALL_USERS);
//
//            List<User> users = new ArrayList<>();
//
//            while (result.next()) {
//                users.add(userRowMapper.mapRow(result));
//            }
//            return users;
//        } catch(SQLException e) {
//            throw new IllegalStateException(e);
//        }
        return jdbcTemplate.queryForList(SQL_FIND_ALL_USERS, userRowMapper);
    }

    @Override
    public void save(User entity) {

    }

    @Override
    public void delete(User entity) {

    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void deleteById(Long id) {

    }
}
