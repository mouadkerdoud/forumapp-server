package com.duodrek.forumappserver.service;


import com.duodrek.forumappserver.model.Post;
import com.duodrek.forumappserver.model.User;
import com.duodrek.forumappserver.repository.PostRepository;
import com.duodrek.forumappserver.repository.UserRepository;
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

    @Autowired
    UserRepository userRepository;

    @Override
    public Post savePost(Post post){
        return postRepository.save(post);
    }

    @Override
    public Post updatePost(Post post){
        Post existingPost = postRepository.findById(post.getPostId()).orElse(null);
        existingPost.setPostTile(post.getPostTile());
        existingPost.setPostLongDescription(post.getPostLongDescription());
        existingPost.setPostShortDescription(post.getPostShortDescription());
        return postRepository.save(existingPost);
    }

    @Override
    public void deletePost(Long postId){
        postRepository.deleteById(postId);
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
