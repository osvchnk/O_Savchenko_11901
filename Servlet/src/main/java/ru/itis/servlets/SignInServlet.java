package ru.itis.servlets;

import ru.itis.repositories.UsersRepository;
import ru.itis.repositories.UsersRepositoryJdbcImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * На GET-запрос предоставлять страницу для входа
 * На пост запрос получает login и password, ищет в базе (с помощью Repository)
 * если такой пользователь с таким логином и паролем есть в базе
 * то отдать ему cookie с UUID (этот UUID также нужно запомнить в базе)
 */

@WebServlet("/signin")
public class SignInServlet extends HttpServlet {

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
        request.getRequestDispatcher("WEB-INF/jsp/signin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (usersRepository.findByLoginPassword(login, password).isPresent()) {
            request.getSession().setAttribute("user", usersRepository.findByLoginPassword(login, password).get());
            response.sendRedirect("/user-app/profile");
        }
        else {
            response.getWriter().print("Invalid username or password");
        }
    }
}
