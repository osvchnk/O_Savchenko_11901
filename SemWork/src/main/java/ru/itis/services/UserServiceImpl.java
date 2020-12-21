package ru.itis.services;

import ru.itis.dto.UserDto;
import ru.itis.models.Post;
import ru.itis.models.User;
import ru.itis.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository) {this.userRepository = userRepository;}

    @Override
    public List<UserDto> findUsersByNames(String prefix, Integer amount) {
        List<User> users = userRepository.findUsersByNames(prefix.toLowerCase());
        if (users.size() > amount) {
            users = users.subList(0, amount - 1);
        }
        List<UserDto> userDtoList = new ArrayList<>();
        for(User user : users) {
            userDtoList.add(UserDto.from(user));
        }
        return userDtoList;
    }

    @Override
    public UserDto findById(Long id) {
        return UserDto.from(userRepository.findById(id).get());

    }

    @Override
    public void like(UserDto user, Long postId) {
        userRepository.like(user, postId);
    }

    @Override
    public void dislike(UserDto user, Long postId) {
        userRepository.dislike(user, postId);
    }
}
