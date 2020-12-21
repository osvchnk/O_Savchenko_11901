package ru.itis.servlets;

import org.json.JSONArray;
import org.json.JSONObject;
import ru.itis.dto.UserDto;
import ru.itis.models.FileInfo;
import ru.itis.models.Post;
import ru.itis.services.FileService;
import ru.itis.services.PostService;
import ru.itis.services.SignInService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/profile/posts")
public class PostServlet extends HttpServlet {

    private PostService postService;
    private FileService fileService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserDto user = (UserDto) request.getSession().getAttribute("user");
        Long userId = user.getId();

        List<Post> posts = postService.getPostsForUser(15, userId);

        JSONArray jsonArray = new JSONArray();
        for(Post post : posts) {
            //get first file for posts presentation
            JSONObject jsonObject = new JSONObject();

            List<FileInfo> files = fileService.findByPostId(post.getId());
            if (files.size() != 0) {
                Long fileId = files.get(0).getId();
                jsonObject.put("file", fileId);
            }

            jsonObject.put("id", post.getId());
            jsonObject.put("name", post.getName());
            jsonObject.put("description", post.getDescription());
            jsonObject.put("date", post.getDate());
            jsonObject.put("tag", post.getTag());
            jsonObject.put("likes", post.getLikes());

            jsonArray.put(jsonObject);
        }

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print(jsonArray);
        out.flush();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        this.postService = (PostService) servletContext.getAttribute("postService");
        this.fileService = (FileService) servletContext.getAttribute("fileService");
    }
}
