package com.duodrek.forumappserver.service;


import com.duodrek.forumappserver.model.Post;
import com.duodrek.forumappserver.model.User;
import com.duodrek.forumappserver.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PostServiceImplementation implements PostService {

    @Autowired
    PostRepository postRepository;

    @Override
    public Post savePost(Post post){
        return postRepository.save(post);
    }

    @Override
    public Post updatePost(Post post){
        return postRepository.save(post);
    }

    @Override
    public void deletePost(Long postId){
        postRepository.deleteById(postId);
    }

    @Override
    public List<Post> findPostsByUser(User user, Sort sort){
        return postRepository.findByUser(user, sort);
    }

    @Override
    public List<Post> getAllPosts(){
        return postRepository.findAll();
    }

    @Override
    public Long numberOfPosts(){
        return postRepository.count();
    }

}
