package ru.itis.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.itis.dto.UserDto;
import ru.itis.models.User;
import ru.itis.services.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {

    private UserService userService;
    private ObjectMapper objectMapper;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String prefix = request.getReader().readLine();
        List<UserDto> users = userService.findUsersByNames(prefix, 5);

        String usersAsJson = objectMapper.writeValueAsString(users);
        response.setContentType("application/json");
        response.getWriter().println(usersAsJson);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        objectMapper = new ObjectMapper();
        ServletContext servletContext = config.getServletContext();
        this.userService = (UserService) servletContext.getAttribute("userService");
    }
}
