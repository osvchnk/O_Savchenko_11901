package ru.itis.services;

import ru.itis.models.Post;
import ru.itis.models.User;
import ru.itis.repositories.PostRepository;
import ru.itis.repositories.PostRepositoryImpl;

import java.util.List;

public class PostServiceImpl implements PostService {

    //now without popularity
    //it need to correct in sql request

    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    public List<Post> returnSublist(List<Post> posts, Integer amount) {
        if (posts.size() <= amount) {
            return posts;
        }
        return posts.subList(0, amount - 1);
    }

    @Override
    public void save(Post project) {
        postRepository.save(project);
    }

    @Override
    public void delete(Long id) {
        postRepository.delete(id);
    }

    @Override
    public Long getLastPostId(Long userId) {
        return postRepository.getLastPostId(userId);
    }

    @Override
    public void liked(Post post) {
        post.setLikes(post.getLikes() + 1);
        postRepository.update(post);
    }

    @Override
    public void unliked(Post post) {
        post.setLikes(post.getLikes() - 1);
        postRepository.update(post);
    }

    @Override
    public List<Post> getPopularPosts(Integer amount) {
        List<Post> posts = postRepository.findAll();
        return returnSublist(posts, amount);
    }

    @Override
    public List<Post> getPopularPostsByTag(Integer amount, String tag) {
        List<Post> posts = postRepository.findByTag(tag);
        return returnSublist(posts, amount);
    }

    @Override
    public List<Post> getPostsForUser(Integer amount, Long userId) {
        List<Post> posts = postRepository.findByUserId(userId);
        return returnSublist(posts, amount);
    }
}