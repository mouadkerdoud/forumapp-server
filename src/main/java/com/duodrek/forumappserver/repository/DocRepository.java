package com.duodrek.forumappserver.repository;

import com.duodrek.forumappserver.model.Doc;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocRepository  extends JpaRepository<Doc,Integer> {

}