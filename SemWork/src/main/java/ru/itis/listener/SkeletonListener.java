package ru.itis.listener;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.repositories.*;
import ru.itis.services.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

@WebListener
public class SkeletonListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        ServletContext servletContext = servletContextEvent.getServletContext();

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/semwork");
        dataSource.setUsername("postgres");
        dataSource.setPassword("savchenkol");

        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        UserRepository userRepository = new UserRepositoryImpl(dataSource);
        FileRepository fileRepository = new FileRepositoryImpl(dataSource);
        PostRepository postRepository = new PostRepositoryImpl(dataSource);

        SignUpService signUpService = new SignUpServiceImpl(userRepository, passwordEncoder);
        SignInService signInService = new SignInServiceImpl(userRepository, passwordEncoder);
        FileService fileService = new FileServiceImpl(fileRepository);
        PostService postService = new PostServiceImpl(postRepository);
        UserService userService = new UserServiceImpl(userRepository);
        servletContext.setAttribute("signUpService", signUpService);
        servletContext.setAttribute("signInService", signInService);
        servletContext.setAttribute("fileService", fileService);
        servletContext.setAttribute("postService", postService);
        servletContext.setAttribute("userService", userService);
        servletContext.setAttribute("validator", validator);

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
