package com.duodrek.forumappserver.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.minidev.json.annotate.JsonIgnore;


import javax.persistence.*;


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

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
}
