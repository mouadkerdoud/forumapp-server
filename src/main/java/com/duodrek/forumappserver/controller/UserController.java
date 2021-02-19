package com.duodrek.forumappserver.controller;

import com.duodrek.forumappserver.jwt.JwtTokenProvider;
import com.duodrek.forumappserver.model.*;
import com.duodrek.forumappserver.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @Autowired
    private DocStorageService docStorageService;



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



    //Doc

    @GetMapping("/api/user/cvs")
    public ResponseEntity<?> getAllCvs(){
        return new ResponseEntity<>(docStorageService.getFiles(), HttpStatus.OK);
    }


    @PostMapping("/api/user/uploadCv")
    public ResponseEntity<?> uploadFiles(@RequestParam("files") MultipartFile file, @RequestParam("userId") Long userId) {

        return new ResponseEntity<>( docStorageService.saveFile(file, userId), HttpStatus.CREATED );
    }

    @GetMapping("/api/user/downloadFile/{fileId}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Integer fileId){
        Doc doc = docStorageService.getFile(fileId).get();
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(doc.getDocType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment:filename=\""+doc.getDocName()+"\"")
                .body(new ByteArrayResource(doc.getData()));
    }





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
