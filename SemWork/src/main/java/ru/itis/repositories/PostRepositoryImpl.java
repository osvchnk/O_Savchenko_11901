package ru.itis.repositories;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.models.Post;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class PostRepositoryImpl implements PostRepository {

    //language=SQL
    private static final String SQL_FIND_BY_ID = "select * from project where id = ?";
    //language=SQL
    private static final String SQL_FIND_ALL = "select * from project order by date desc";
    //language=SQL
    private static final String SQL_FIND_ALL_BY_USER_ID = "select * from project where user_id = ?";
    //language=SQL
    private static final String SQL_FIND_ALL_BY_TAG = "select * from project where tag = ?";
    //language=SQL
    private static final String SQL_INSERT = "insert into project(user_id, tag, date, likes, name, description) " +
            "values (?, ?, ?, ?, ?, ?)";
    //language=SQL
    private static final String SQL_DELETE_BY_ID = "delete from project where id = ?";
    //language=SQL
    private static final String SQL_UPDATE =
            "update project set user_id = ?, tag = ?, date = ?, likes = ?, name = ?, description = ? where id = ?";
    //language=SQL
    private static final String SQL_FIND_LAST_POST = "select * from project where user_id = ? order by id desc limit 1";


    private JdbcTemplate jdbcTemplate;

    public PostRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<Post> postRowMapper = (row, rowNumber) -> Post.builder()
            .id(row.getLong("id"))
            .userId(row.getLong("user_id"))
            .name(row.getString("name"))
            .description(row.getString("description"))
            .tag(row.getString("tag"))
            .date(row.getDate("date"))
            .likes(row.getLong("likes"))
            .build();

    @Override
    public List<Post> findByUserId(Long userId) {
        return jdbcTemplate.query(SQL_FIND_ALL_BY_USER_ID, postRowMapper, userId);
    }

    @Override
    public List<Post> findByTag(String tag) {
        return jdbcTemplate.query(SQL_FIND_ALL_BY_TAG, postRowMapper, tag);
    }

    @Override
    public Long getLikesById(Long id) {
        Post post = jdbcTemplate.queryForObject(SQL_FIND_BY_ID, postRowMapper, id);
        assert post != null;
        return post.getLikes();
    }

    @Override
    public Long getLastPostId(Long userId) {
        Post post = jdbcTemplate.queryForObject(SQL_FIND_LAST_POST, postRowMapper, userId);
        assert post != null;
        return post.getId();
    }

    @Override
    public void save(Post entity) {
        jdbcTemplate.update(SQL_INSERT, entity.getUserId(),
                entity.getTag(),
                entity.getDate(),
                entity.getLikes(),
                entity.getName(),
                entity.getDescription());
    }

    @Override
    public void update(Post entity) {
        jdbcTemplate.update(SQL_UPDATE, entity.getUserId(), entity.getTag(), entity.getDate(), entity.getLikes(),
                entity.getName(), entity.getDescription(), entity.getId());
    }


    @Override
    public void delete(Long id) {
        jdbcTemplate.update(SQL_DELETE_BY_ID, id);
    }

    @Override
    public Optional<Post> findById(Long id) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(SQL_FIND_BY_ID, postRowMapper, id));
        } catch(EmptyResultDataAccessException ex){
            return Optional.empty();
        }
    }

    @Override
    public List<Post> findAll() {
        return jdbcTemplate.query(SQL_FIND_ALL, postRowMapper);
    }
}
