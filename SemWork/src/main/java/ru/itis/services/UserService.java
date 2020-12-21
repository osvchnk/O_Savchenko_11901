package ru.itis.services;

import ru.itis.dto.UserDto;
import ru.itis.models.Post;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDto> findUsersByNames(String prefix, Integer amount);
    UserDto findById(Long id);
    void like(UserDto user, Long postId);
    void dislike(UserDto user, Long postId);
}
