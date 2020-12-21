package ru.itis.repositories;

import ru.itis.models.FileInfo;

import java.util.List;
import java.util.Optional;

public interface FileRepository extends CrudRepository<FileInfo>{
    List<FileInfo> findByPostId(Long id);
}