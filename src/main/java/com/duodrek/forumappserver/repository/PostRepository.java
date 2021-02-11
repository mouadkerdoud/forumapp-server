package com.duodrek.forumappserver.repository;

import com.duodrek.forumappserver.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PostRepository extends JpaRepository<Post,Long> {
}
