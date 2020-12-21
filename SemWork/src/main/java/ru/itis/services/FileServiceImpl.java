package ru.itis.services;

import ru.itis.models.FileInfo;
import ru.itis.repositories.FileRepository;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

public class FileServiceImpl implements FileService {

    private FileRepository fileRepository;

    public FileServiceImpl(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public void saveFileToStorage(InputStream file, String originalFileName, String contentType, Long size, Long postId) {
        FileInfo fileInfo = FileInfo.builder()
                .originalFileName(originalFileName)
                .storageFileName(UUID.randomUUID().toString())
                .type(contentType)
                .size(size)
                .postId(postId)
                .build();
        try {
            Files.copy(file, Paths.get("C://files/" + fileInfo.getStorageFileName() + "." + fileInfo.getType().split("/")[1]));
            fileRepository.save(fileInfo);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void readFileFromStorage(Long fileId, OutputStream outputStream) {
        // должно возвращать optional
        FileInfo fileInfo = fileRepository.findById(fileId).get();
        File file = new File("C://files/" + fileInfo.getStorageFileName() + "."
                + fileInfo.getType().split("/")[1]);
        try {
            Files.copy(file.toPath(), outputStream);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public FileInfo getFileInfo(Long fileId) {
        return fileRepository.findById(fileId).get();
    }

    @Override
    public List<FileInfo> findByPostId(Long postId) {
        return fileRepository.findByPostId(postId);
    }
}
