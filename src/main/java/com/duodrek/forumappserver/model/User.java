package com.duodrek.forumappserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 255)
    @Column(name="username")
    private String username;

    @Size(max = 255)
    @Column(name="password")
    private String password;

    @Size(max = 255)
    @Column(name="firstName")
    private String firstName;

    @Size(max = 255)
    @Column(name="lastName")
    private String lastName;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Post> posts;

    @Enumerated(EnumType.STRING)
    @Column(name="role")
    private Role role;

}
