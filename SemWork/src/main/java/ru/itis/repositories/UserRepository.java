package ru.itis.repositories;
import ru.itis.dto.UserDto;
import ru.itis.models.Post;
import ru.itis.models.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User>{
    Optional<User> findByEmail(String email);
    void like(UserDto user, Long postId);
    void dislike(UserDto user, Long postId);
    List<User> findUsersByNames(String prefix);
}
