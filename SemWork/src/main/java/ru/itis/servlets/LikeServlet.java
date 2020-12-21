package ru.itis.servlets;

import ru.itis.dto.UserDto;
import ru.itis.models.Post;
import ru.itis.models.User;
import ru.itis.repositories.UserRepository;
import ru.itis.services.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/like")
public class LikeServlet extends HttpServlet {

    private UserService userService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        UserDto userDto = (UserDto) request.getSession().getAttribute("user");
        Long postId = Long.parseLong(request.getParameter("postId"));
        userService.like(userDto, postId);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        this.userService = (UserService) servletContext.getAttribute("userService");
    }
}
