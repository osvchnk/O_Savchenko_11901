package ru.itis.services;

import ru.itis.models.FileInfo;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public interface FileService {
    void saveFileToStorage(InputStream file, String originalFileName, String contentType, Long size, Long postId);
    void readFileFromStorage(Long fileId, OutputStream outputStream);
    FileInfo getFileInfo(Long fileId);
    List<FileInfo> findByPostId(Long postId);
}
