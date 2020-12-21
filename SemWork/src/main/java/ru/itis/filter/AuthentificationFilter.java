package ru.itis.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class AuthentificationFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ServletContext servletContext = filterConfig.getServletContext();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // преобразуем запросы к нужному виду
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // берем сессию у запроса
        // берем только существующую, если сессии не было - то вернет null
        HttpSession session = request.getSession(false);
        // флаг, аутентифицирован ли пользователь
        Boolean isAuthenticated = false;
        // существует ли сессия вообще?
        Boolean sessionExists = session != null;
        // идет ли запрос на страницу входа или регистрации?
        Boolean isRequestOnEntry = request.getRequestURI().equals("/signIn") ||
                request.getRequestURI().equals("/signUp") ||
                request.getRequestURI().equals("/test");
        Boolean isRequestOnClosedPage = request.getRequestURI().equals("/profile") || request.getRequestURI().equals("/like");
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

        boolean isStaticResource = request.getRequestURI().startsWith("/resources/");

        // если сессия есть
        if (sessionExists) {
            // проверим, есть ли атрибует user?
            isAuthenticated = session.getAttribute("user") != null;
        }
        if (!isAuthenticated && isRequestOnClosedPage) {
            response.sendRedirect("/signIn");
        } else if (isAuthenticated && isRequestOnEntry) {
            response.sendRedirect("/profile");
        } else {
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}