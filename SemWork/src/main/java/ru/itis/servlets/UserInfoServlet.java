package ru.itis.servlets;


import org.json.JSONObject;
import ru.itis.dto.UserDto;
import ru.itis.services.PostService;
import ru.itis.services.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/userInfo")
public class UserInfoServlet extends HttpServlet {

    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDto user = (UserDto) request.getSession().getAttribute("user");

        UserDto userDto = userService.findById(user.getId());

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("firstName", userDto.getFirstName());
        jsonObject.put("lastName", userDto.getLastName());
        jsonObject.put("email", userDto.getEmail());

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print(jsonObject);
        out.flush();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        this.userService = (UserService) servletContext.getAttribute("userService");
    }
}
