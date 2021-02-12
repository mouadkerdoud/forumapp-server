package com.duodrek.forumappserver.repository;

import com.duodrek.forumappserver.model.Attending;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendingRepository extends JpaRepository<Attending, Long> {
}
