package ru.itis.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class FileInfo {
    private Long id;
    private Long postId;
    private String originalFileName;
    private String storageFileName;
    private String type;
    private Long size;
}
