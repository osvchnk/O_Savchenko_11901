package ru.itis.servlets;

import ru.itis.dto.UserForm;
import ru.itis.services.SignUpService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import java.io.IOException;
import java.util.Set;

@WebServlet("/signUp")
public class SignUpServlet extends HttpServlet {

    private SignUpService signUpService;
    private Validator validator;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        this.signUpService = (SignUpService) servletContext.getAttribute("signUpService");
        this.validator = (Validator) servletContext.getAttribute("validator");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/sign_up.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserForm userForm = UserForm.builder()
                .firstName(request.getParameter("firstName"))
                .lastname(request.getParameter("lastName"))
                .email(request.getParameter("email"))
                .password(request.getParameter("password"))
                .build();

        System.out.println(userForm);

        Set<ConstraintViolation<UserForm>> constraintViolations = validator.validate(userForm);

        if (!constraintViolations.isEmpty()) {
            request.setAttribute("errors", constraintViolations);
            request.getRequestDispatcher("/WEB-INF/jsp/sign_up.jsp").forward(request, response);
        } else {
            signUpService.signUp(userForm);
            response.sendRedirect("/signIn");
        }
    }
}
