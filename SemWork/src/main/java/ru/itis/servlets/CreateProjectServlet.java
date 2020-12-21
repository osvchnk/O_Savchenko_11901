package ru.itis.servlets;

import ru.itis.dto.UserDto;
import ru.itis.models.Post;
import ru.itis.services.FileService;
import ru.itis.services.PostService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;

@WebServlet("/profile/newProject")
@MultipartConfig
public class CreateProjectServlet extends HttpServlet {

    // a description can be added into post

    private PostService postService;
    private FileService fileService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
//        Enumeration<String> params = request.getParameterNames();
//        while(params.hasMoreElements()){
//            String paramName = params.nextElement();
//            System.out.println("Parameter Name - "+paramName+", Value - "+request.getParameter(paramName));
//        }

        UserDto user = (UserDto) request.getSession().getAttribute("user");

        Post post = Post.builder()
                .userId(user.getId())
                .tag(request.getParameter("tag"))
                .date(new Date())
                .name(request.getParameter("name"))
                .description(request.getParameter("description"))
                .build();

        postService.save(post);
        Long postId = postService.getLastPostId(user.getId());

        Part part = request.getPart("file");

        fileService.saveFileToStorage(part.getInputStream(),
                part.getSubmittedFileName(),
                part.getContentType(), part.getSize(), postId);
        resp.sendRedirect("/profile");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        this.postService = (PostService) servletContext.getAttribute("postService");
        this.fileService = (FileService) servletContext.getAttribute("fileService");
    }
}
