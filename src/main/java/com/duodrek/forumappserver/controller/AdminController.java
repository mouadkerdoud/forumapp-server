package com.duodrek.forumappserver.controller;

import com.duodrek.forumappserver.model.Post;
import com.duodrek.forumappserver.model.StringResponse;
import com.duodrek.forumappserver.model.User;
import com.duodrek.forumappserver.service.PostService;
import com.duodrek.forumappserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;


    @PostMapping("/api/admin/updateUser")
    public ResponseEntity<?> updateUser(@RequestBody User user){
        User existUser = userService.findByUsername(user.getUsername());
        if(existUser != null && !existUser.getId().equals(user.getId())){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(userService.updateUser(user), HttpStatus.CREATED);
    }

    @DeleteMapping("/api/admin/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/api/admin/users")
    public ResponseEntity<?> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }


    @GetMapping("/api/admin/users-number")
    public ResponseEntity<?> numberOfUsers(){
        Long number = userService.numberOfUsers();
        StringResponse response = new StringResponse();
        response.setResponse(number.toString());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PostMapping("/api/admin/createPost")
    public ResponseEntity<?> createPost(@RequestBody Post post){
        return new ResponseEntity<>(postService.savePost(post), HttpStatus.CREATED);
    }

    @PostMapping("/api/admin/updatePost")
    public ResponseEntity<?> updatePost(@RequestBody Post post){
        return new ResponseEntity<>(postService.updatePost(post), HttpStatus.CREATED);
    }

    @DeleteMapping("/api/admin/deletePost/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId){
        postService.deletePost(postId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/api/admin/posts")
    public ResponseEntity<?> getAllPosts(){
        return new ResponseEntity<>(postService.getAllPosts(), HttpStatus.OK);
    }

    @GetMapping("/api/admin/posts-number")
    public ResponseEntity<?> numberOfPosts(){
        Long number = postService.numberOfPosts();
        StringResponse response = new StringResponse();
        response.setResponse(number.toString());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
