package ru.itis.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.dto.UserDto;
import ru.itis.dto.UserForm;
import ru.itis.models.User;
import ru.itis.repositories.UserRepository;

import java.util.Optional;

public class SignInServiceImpl implements SignInService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public SignInServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto signIn(UserForm userForm) {
        Optional<User> userOptional = userRepository.findByEmail(userForm.getEmail());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(userForm.getPassword(), user.getHashPassword())) {
                return UserDto.from(user);
            } else return null;
        }
        return null;
    }
}
