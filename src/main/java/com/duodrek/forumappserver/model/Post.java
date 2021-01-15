package com.duodrek.forumappserver.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Size(max = 255)
    private String postTile;

    @Size(max = 500)
    private String postShortDescription;

    @Size(max = 1000)
    private String postLongDescription;


    private String username;

    private LocalDateTime publishDate;

}
