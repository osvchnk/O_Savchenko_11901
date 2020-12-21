package ru.itis.repositories;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.models.FileInfo;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class FileRepositoryImpl implements FileRepository {

    //language=SQL
    private static final String SQL_INSERT = "insert into files(project_id, original_file_name, storage_file_name, type, size) values (?, ?, ?, ?, ?)";
    //language=SQL
    private static final String SQL_FIND_BY_ID = "select * from files where id = ?";
    //language=SQL
    private static final String SQL_FIND_ALL = "select * from files";
    //language=SQL
    private static final String SQL_FIND_ALL_BY_POST_ID = "select * from files where project_id = ?";
    //language=SQL
    private static final String SQL_DELETE = "delete from files where id = ?";

    private JdbcTemplate jdbcTemplate;

    public FileRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public RowMapper<FileInfo> fileInfoRowMapper = (row, rowNumber) -> FileInfo.builder()
            .id(row.getLong("id"))
            .postId(row.getLong("project_id"))
            .originalFileName(row.getString("original_file_name"))
            .storageFileName(row.getString("storage_file_name"))
            .type(row.getString("type"))
            .size(row.getLong("size"))
            .build();

    @Override
    public List<FileInfo> findByPostId(Long postId) {
        return jdbcTemplate.query(SQL_FIND_ALL_BY_POST_ID, fileInfoRowMapper, postId);
    }

    @Override
    public void save(FileInfo entity) {
        jdbcTemplate.update(SQL_INSERT, entity.getPostId(),
                entity.getOriginalFileName(),
                entity.getStorageFileName(),
                entity.getType(),
                entity.getSize());
    }

    @Override
    public void update(FileInfo entity) {

    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(SQL_DELETE, id);
    }

    @Override
    public Optional<FileInfo> findById(Long id) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(SQL_FIND_BY_ID, fileInfoRowMapper, id));
        } catch(EmptyResultDataAccessException ex) {
            return Optional.empty();
        }
    }

    @Override
    public List<FileInfo> findAll() {
        return jdbcTemplate.query(SQL_FIND_ALL, fileInfoRowMapper);
    }
}
