package ru.itis.repositories;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T> {

    public void save(T entity);

    public void update(T entity);

    public void delete(Long id);

    Optional<T> findById(Long id);

    List<T> findAll();
}
