package com.duodrek.forumappserver.controller;

import com.duodrek.forumappserver.model.Event;
import com.duodrek.forumappserver.model.Post;
import com.duodrek.forumappserver.model.StringResponse;
import com.duodrek.forumappserver.model.User;
import com.duodrek.forumappserver.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Autowired
    private EventService eventService;

    @Autowired
    private DocStorageService docStorageService;

    @Autowired
    private AttendingService attendingService;

    //User methods

    @PostMapping("/api/admin/add-user")
    public ResponseEntity<?> addNewUser(@RequestBody User user){
        if(userService.findByUsername(user.getUsername()) != null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }

    @PutMapping("/api/admin/updateUser")
    public ResponseEntity<?> updateUser(@RequestBody User user){
        User existUser = userService.findByUsername(user.getUsername());
        if(existUser != null && !existUser.getUserId().equals(user.getUserId())){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(userService.updateUser(user), HttpStatus.CREATED);
    }

    @DeleteMapping("/api/admin/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/api/admin/findUserById/{userId}")
    public ResponseEntity<?> findByUserId(@PathVariable Long userId){
        return new ResponseEntity<>(userService.findByUserId(userId), HttpStatus.OK);
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



    //Doc methods


    //Upload methods
    @PostMapping("/api/admin/uploadUserDoc")
    public ResponseEntity<?> uploadFilesUser(@RequestParam("files") MultipartFile file, @RequestParam("userId") Long userId) {

        return new ResponseEntity<>( docStorageService.saveFileUser(file, userId), HttpStatus.CREATED );
    }

    @PutMapping("/api/admin/updateUserFile")
    public ResponseEntity<?> updateFileUser(@RequestParam("files") MultipartFile file, @RequestParam("userId") Long userId) {

        return new ResponseEntity<>( docStorageService.updateFileUser(file, userId), HttpStatus.CREATED );
    }



    //Retrieving methods
    @GetMapping("/api/admin/findDocByUserId/{userId}")
    public ResponseEntity<?> findDocByUserId(@PathVariable Long userId){
        return new ResponseEntity<>(docStorageService.findByUserId(userId), HttpStatus.OK);
    }

    @GetMapping("/api/admin/docs")
    public ResponseEntity<?> getAllDocs(){
        return new ResponseEntity<>(docStorageService.getFiles(), HttpStatus.OK);
    }




    //Post methods

    @PostMapping("/api/admin/createPost")
    public ResponseEntity<?> createPost(@RequestBody Post post){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatDateTime = now.format(formatter);
        post.setPublishDate(formatDateTime);
        return new ResponseEntity<>(postService.savePost(post), HttpStatus.CREATED);
    }

    @PutMapping("/api/admin/updatePost")
    public ResponseEntity<?> updatePost(@RequestBody Post post){
        return new ResponseEntity<>(postService.updatePost(post), HttpStatus.CREATED);
    }

    @DeleteMapping("/api/admin/deletePost/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId){
        postService.deletePost(postId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/api/admin/findPostById/{postId}")
    public ResponseEntity<?> findPostById(@PathVariable Long postId){
        return new ResponseEntity<>(postService.findPostById(postId), HttpStatus.OK);
    }

    @GetMapping("/api/admin/posts-all")
    public ResponseEntity<?> findAllPosts(){
        return new ResponseEntity<>(postService.getAllPosts(), HttpStatus.OK);
    }



    @GetMapping("/api/admin/posts-number")
    public ResponseEntity<?> numberOfPosts(){
        Long number = postService.numberOfPosts();
        StringResponse response = new StringResponse();
        response.setResponse(number.toString());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



    //Event methods

    @PostMapping("/api/admin/createEvent")
    public ResponseEntity<?> createEvent(@RequestBody Event event){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatDateTime = now.format(formatter);
        event.setPublishDate(formatDateTime);
        return new ResponseEntity<>(eventService.saveEvent(event), HttpStatus.CREATED);
    }

    @PutMapping("/api/admin/updateEvent")
    public ResponseEntity<?> updatePost(@RequestBody Event event){
        return new ResponseEntity<>(eventService.updateEvent(event), HttpStatus.CREATED);
    }

    @DeleteMapping("/api/admin/deleteEvent/{eventId}")
    public ResponseEntity<?> deleteEvent(@PathVariable Long eventId){
        eventService.deleteEvent(eventId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/api/admin/findEventById/{eventId}")
    public ResponseEntity<?> findEventById(@PathVariable Long eventId){
        return new ResponseEntity<>(eventService.findEventById(eventId), HttpStatus.OK);
    }

    @GetMapping("/api/admin/events-all")
    public ResponseEntity<?> findAllEvents(){
        return new ResponseEntity<>(eventService.getAllEvents(), HttpStatus.OK);
    }

    @GetMapping("/api/admin/events-number")
    public ResponseEntity<?> numberOfEvents() {
        Long number = eventService.numberOfEvents();
        StringResponse response = new StringResponse();
        response.setResponse(number.toString());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



    //Attendings methods
    @GetMapping("/api/admin/attendings")
    public ResponseEntity<?> getAllAttendings(){
        return new ResponseEntity<>(attendingService.findAllAttendings(), HttpStatus.OK);
    }

    @DeleteMapping("/api/admin/deleteAttending/{attendingId}")
    public ResponseEntity<?> deleteAttending(@PathVariable Long attendingId){
        attendingService.deleteAttending(attendingId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/api/admin/attendings-number")
    public ResponseEntity<?> numberOfAttendings(){
        Long number = attendingService.numberOfAttendings();
        StringResponse response = new StringResponse();
        response.setResponse(number.toString());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
