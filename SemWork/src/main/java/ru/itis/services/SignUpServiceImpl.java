package ru.itis.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.dto.UserForm;
import ru.itis.models.User;
import ru.itis.repositories.UserRepository;

public class SignUpServiceImpl implements SignUpService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public SignUpServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void signUp(UserForm userForm) {

        User user = User.builder()
                .firstName(userForm.getFirstName())
                .lastName(userForm.getLastname())
                .email(userForm.getEmail())
                .hashPassword(passwordEncoder.encode(userForm.getPassword()))
                .build();
        userRepository.save(user);
    }
}
