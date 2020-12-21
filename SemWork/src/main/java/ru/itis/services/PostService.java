package ru.itis.services;

import ru.itis.models.Post;
import ru.itis.models.User;

import java.util.List;

public interface PostService {

    //now without popularity
    //it needs to correct in sql request

    void save(Post post);
    void delete(Long id);
    Long getLastPostId(Long userId);

    void liked(Post post);
    void unliked(Post post);

    List<Post> getPopularPosts(Integer amount);
    List<Post> getPopularPostsByTag(Integer amount, String tag);
    List<Post> getPostsForUser(Integer amount, Long userId);
}
