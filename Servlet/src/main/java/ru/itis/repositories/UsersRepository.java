package ru.itis.repositories;

import ru.itis.models.User;

import java.util.List;

public interface UsersRepository extends CrudRepository<User>{
    List<User> findAllByAge(Integer age);
    boolean exist(String login, String password);
}
