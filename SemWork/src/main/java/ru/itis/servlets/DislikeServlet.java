package ru.itis.servlets;

import ru.itis.dto.UserDto;
import ru.itis.services.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/dislike")
public class DislikeServlet extends HttpServlet {

    private UserService userService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        UserDto user = (UserDto) request.getSession().getAttribute("user");
        Long postId = Long.parseLong(request.getParameter("postId"));
        userService.dislike(user, postId);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        this.userService = (UserService) servletContext.getAttribute("userService");
    }
}
