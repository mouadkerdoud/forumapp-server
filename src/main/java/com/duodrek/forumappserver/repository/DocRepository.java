package com.duodrek.forumappserver.repository;

import com.duodrek.forumappserver.model.Doc;
import com.duodrek.forumappserver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DocRepository  extends JpaRepository<Doc,Integer> {
    Optional<Doc> findByUserId(Long userId);
}