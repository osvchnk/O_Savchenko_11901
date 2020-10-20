package ru.itis.servlets;

import ru.itis.models.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/show")
public class UserServlet extends HttpServlet {

    private static final String DB_USER = "postgres";
    private static final String DB_DRIVER = "org.postgresql.Driver";
    private static final String DB_PASSWORD = "*";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/11-901";

    private List<User> users;

    public void init() {
        this.users = new ArrayList<User>();
        try {
            Class.forName(DB_DRIVER);
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("select * from app_user");
            while(result.next()){
                User user = User.builder()
                        .id(result.getLong(1))
                        .first_name(result.getString("first_name"))
                        .last_name(result.getString("last_name"))
                        .build();
                users.add(user);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        init();
        request.setAttribute("usersForJsp", users);
        request.getRequestDispatcher("WEB-INF/jsp/users.jsp").forward(request, response);
    }
}
