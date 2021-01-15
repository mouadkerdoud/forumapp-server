package com.duodrek.forumappserver.repository;

import com.duodrek.forumappserver.model.Post;
import com.duodrek.forumappserver.model.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {
}
