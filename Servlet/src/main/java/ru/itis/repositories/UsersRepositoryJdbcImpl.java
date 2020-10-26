package ru.itis.repositories;

import ru.itis.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UsersRepository{

    private static final String SQL_FIND_ALL_USERS = "select * from app_user";
    private static final String SQL_FIND_ALL_USERS_BY_AGE = "select * from app_user where age = ?";
    private static final String SQL_FIND_ALL_USERS_BY_LOGIN_AND_PASSWORD = "select * from app_user where login = ? and " +
            "password = ?";

    private Connection connection;
    private SimpleJdbcTemplate jdbcTemplate;

    private RowMapper<User> userRowMapper = row -> User.builder().
            id(row.getLong("id")).
            first_name(row.getString("first_name")).
            last_name(row.getString("last_name")).
            age(row.getInt("age")).
            login(row.getString("login")).
            password(row.getString("password")).
            uuid(row.getString("uuid")).
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
    public List<User> findAllByFirstNamePrefix(String prefix) {
        String sql = "select * from app_user where first_name like '"+ prefix +"%'";
        return jdbcTemplate.queryForList(sql, userRowMapper);
    }

    @Override
    public Optional<User> findByLoginPassword(String login, String password) {
        if(jdbcTemplate.queryForList(SQL_FIND_ALL_USERS_BY_LOGIN_AND_PASSWORD, userRowMapper, login, password).isEmpty()){
            return Optional.empty();
        }
        User user = jdbcTemplate.queryForList(SQL_FIND_ALL_USERS_BY_LOGIN_AND_PASSWORD, userRowMapper, login, password).get(0);
        return Optional.of(user);
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
        Long id = entity.getId();
        String query = "UPDATE app_user SET first_name = ?, last_name = ?," +
                "login = ?, password = ?, age = ?, uuid = ? where id = " + id;
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, entity.getFirst_name());
            statement.setString(2, entity.getLast_name());
            statement.setString(3, entity.getLogin());
            statement.setString(4, entity.getPassword());
            statement.setInt(5, entity.getAge());
            statement.setString(6, entity.getUuid());
            System.out.println(statement.toString());
            statement.executeQuery();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void deleteById(Long id) {

    }
}
