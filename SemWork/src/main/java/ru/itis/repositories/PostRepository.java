package ru.itis.repositories;

import ru.itis.models.Post;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends CrudRepository<Post> {
    List<Post> findByUserId(Long UserId);
    List<Post> findByTag(String tag);
    Long getLikesById(Long id);
    Long getLastPostId(Long userId);
}