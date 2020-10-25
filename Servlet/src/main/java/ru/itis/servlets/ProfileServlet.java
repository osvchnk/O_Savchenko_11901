package ru.itis.servlets;

import ru.itis.models.User;
import ru.itis.repositories.UsersRepository;
import ru.itis.repositories.UsersRepositoryJdbcImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.UUID;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    private static final String DB_USER = "postgres";
    private static final String DB_DRIVER = "org.postgresql.Driver";
    private static final String DB_PASSWORD = "savchenkol";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/11-901";

    private UsersRepository usersRepository;

    @Override
    public void init() {
        try {
            Class.forName(DB_DRIVER);
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            usersRepository = new UsersRepositoryJdbcImpl(connection);
        } catch (SQLException | ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");

        if (user.getUuid() == null) {
            String uuid = UUID.randomUUID().toString();
            user.setUuid(uuid);
            usersRepository.update(user);
        }

        Cookie cookie = new Cookie("uuid", user.getUuid());
        response.addCookie(cookie);

        request.getRequestDispatcher("WEB-INF/jsp/profile.jsp").forward(request,response);
    }
}
