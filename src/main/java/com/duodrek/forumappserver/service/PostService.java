package com.duodrek.forumappserver.service;

import com.duodrek.forumappserver.model.Post;
import com.duodrek.forumappserver.model.User;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface PostService {
    Post savePost(Post post);

    Post updatePost(Post post);

    void deletePost(Long postId);

    Post findPostById(Long postId);

    List<Post> getAllPosts();

    Long numberOfPosts();
}
