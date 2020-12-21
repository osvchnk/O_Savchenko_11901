package ru.itis.servlets;

import ru.itis.models.FileInfo;
import ru.itis.models.Post;
import ru.itis.services.FileService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/files")
public class FileServlet extends HttpServlet {

    private FileService fileService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        FileInfo fileInfo = fileService.getFileInfo(Long.parseLong(id));
        resp.setContentType(fileInfo.getType());
        resp.setContentLength(fileInfo.getSize().intValue());
        resp.setHeader("Content-Disposition", "filename=/" + fileInfo.getOriginalFileName() + "/");
        fileService.readFileFromStorage(Long.parseLong(id), resp.getOutputStream());
        resp.flushBuffer();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        this.fileService = (FileService) servletContext.getAttribute("fileService");
    }
}
