package com.duodrek.forumappserver.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column(length = 500)
    private String postTitle;

    @Column(length = 800)
    private String postShortDescription;

    @Column(length = 3000)
    private String postLongDescription;


    private String username;

    private String publishDate;

}
