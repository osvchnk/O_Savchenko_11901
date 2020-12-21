package ru.itis.repositories;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.dto.UserDto;
import ru.itis.models.Post;
import ru.itis.models.User;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {

    //language=SQL
    private static final String SQL_INSERT = "insert into account(first_name, last_name, email, hash_password) values (?, ?, ?, ?)";
    //language=SQL
    private static final String SQL_FIND_BY_ID = "select * from account where id = ?";
    //language=SQL
    private static final String SQL_FIND_BY_EMAIL = "select * from account where email = ?";
    //language=SQL
    private static final String SQL_FIND_ALL = "select * from account";
    //language=SQL
    private static final String SQL_DELETE = "delete from account where id = ?";
    //language=SQL
    private static final String SQL_LIKE = "insert into likes(user_id, project_id) values(?, ?)";
    //language=SQL
    private static final String SQL_DISLIKE = "delete from likes where user_id = ? and project_id = ?";


    private JdbcTemplate jdbcTemplate;

    public UserRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<User> userRowMapper = (row, rowNumber) -> User.builder()
            .id(row.getLong("id"))
            .firstName(row.getString("first_name"))
            .lastName(row.getString("last_name"))
            .email(row.getString("email"))
            .hashPassword(row.getString("hash_password"))
            .build();

    @Override
    public void save(User entity) {
        jdbcTemplate.update(SQL_INSERT, entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getHashPassword());
    }

    @Override
    public void update(User entity) {
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(SQL_DELETE, id);
    }

    @Override
    public Optional<User> findById(Long id) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(SQL_FIND_BY_ID, userRowMapper, id));
        } catch(EmptyResultDataAccessException ex){
            return Optional.empty();
        }
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(SQL_FIND_ALL, userRowMapper);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(SQL_FIND_BY_EMAIL, userRowMapper, email));
        } catch(EmptyResultDataAccessException ex){
            return Optional.empty();
        }
    }

    @Override
    public void like(UserDto user, Long postId) {
        jdbcTemplate.update(SQL_LIKE, user.getId(), postId);
    }

    @Override
    public void dislike(UserDto user, Long postId) {
        jdbcTemplate.update(SQL_DISLIKE, user.getId(), postId);
    }

    @Override
    public List<User> findUsersByNames(String prefix) {
        String SQL_FIND_USERS_BY_NAMES = "select * from account where lower(first_name) || ' ' || lower(last_name) like '"+prefix+"%'  OR\n" +
                "lower(last_name) || ' ' || lower(first_name) like '"+prefix+"%';";
        return jdbcTemplate.query(SQL_FIND_USERS_BY_NAMES, userRowMapper);
    }
}
