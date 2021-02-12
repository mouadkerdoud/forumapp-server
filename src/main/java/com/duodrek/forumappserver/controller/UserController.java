package com.duodrek.forumappserver.controller;

import com.duodrek.forumappserver.jwt.JwtTokenProvider;
import com.duodrek.forumappserver.model.Attending;
import com.duodrek.forumappserver.model.Event;
import com.duodrek.forumappserver.model.Role;
import com.duodrek.forumappserver.model.User;
import com.duodrek.forumappserver.service.AttendingService;
import com.duodrek.forumappserver.service.EventService;
import com.duodrek.forumappserver.service.PostService;
import com.duodrek.forumappserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
public class UserController {

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Autowired
    private EventService eventService;

    @Autowired
    private AttendingService attendingService;



    //User methods

    @PostMapping("/api/user/registration")
    public ResponseEntity<?> register(@RequestBody User user){
        if(userService.findByUsername(user.getUsername()) != null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        user.setRole(Role.USER);
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }

    @GetMapping("/api/user/login")
    public ResponseEntity<?> getUser(Principal principal){
        if(principal == null){
            return ResponseEntity.ok(principal);
        }
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) principal;
        User user = userService.findByUsername(authenticationToken.getName());
        user.setToken(tokenProvider.generateToken(authenticationToken));

        return new ResponseEntity<>(user, HttpStatus.OK);    }


    //Post methods

    @GetMapping("/api/user/posts")
    public ResponseEntity<?> getAllPosts(){
        return new ResponseEntity<>(postService.getAllPosts(), HttpStatus.OK);
    }


    @GetMapping("/api/user/findPostById/{postId}")
    public ResponseEntity<?> findPostById(@PathVariable Long postId){
        return new ResponseEntity<>(postService.findPostById(postId), HttpStatus.OK);
    }


    //Event methods
    @GetMapping("/api/user/events")
    public ResponseEntity<?> getAllEvents(){
        return new ResponseEntity<>(eventService.getAllEvents(), HttpStatus.OK);
    }


    @GetMapping("/api/user/findEventById/{eventId}")
    public ResponseEntity<?> findEventById(@PathVariable Long eventId){
        return new ResponseEntity<>(eventService.findEventById(eventId), HttpStatus.OK);
    }

    @PostMapping("/api/user/attendEvent")
    public ResponseEntity<?> attendEvent(@RequestBody Attending attending){
        attendingService.saveAttending(attending);
        return new ResponseEntity<>(attending, HttpStatus.CREATED);
    }

    @GetMapping("/api/user/attendings")
    public ResponseEntity<?> getAllAttendings(){
        return new ResponseEntity<>(attendingService.findAllAttendings(), HttpStatus.OK);
    }

    @DeleteMapping("/api/user/deleteAttending/{attendingId}")
    public ResponseEntity<?> deleteAttending(@PathVariable Long attendingId){
        attendingService.deleteAttending(attendingId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
