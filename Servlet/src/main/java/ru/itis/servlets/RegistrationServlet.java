package ru.itis.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet("/users")
public class RegistrationServlet extends HttpServlet{

    private static final String DB_USER = "postgres";
    private static final String DB_DRIVER = "org.postgresql.Driver";
    private static final String DB_PASSWORD = "*";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/11-901";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("first_name");
        String lastName = req.getParameter("last_name");

        try {
            Class.forName(DB_DRIVER);
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            String sqlInsertUser = "insert into app_user(first_name, last_name) values (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sqlInsertUser);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.executeUpdate();

        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/html/start.html").forward(req, resp);
    }
}