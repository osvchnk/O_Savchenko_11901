package ru.itis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.itis.models.Post;
import ru.itis.models.User;

@Data
@Builder
@AllArgsConstructor
public class PostDto {
    Long id;
    Long userId;
    Long likes;
    String tag;

    public static PostDto from(Post post) {
        return PostDto.builder()
                .id(post.getId())
                .userId(post.getUserId())
                .likes(post.getLikes())
                .tag(post.getTag())
                .build();
    }
}
