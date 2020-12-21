package ru.itis.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class Post {
    private Long id;
    private Long userId;
    private String name;
    private String description;
    private String tag;
    private Date date;
    private Long likes;
}
