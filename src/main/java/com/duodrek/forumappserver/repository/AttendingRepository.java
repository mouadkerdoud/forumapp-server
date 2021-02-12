package com.duodrek.forumappserver.repository;

import com.duodrek.forumappserver.model.Attending;
import com.duodrek.forumappserver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AttendingRepository extends JpaRepository<Attending, Long> {

}
