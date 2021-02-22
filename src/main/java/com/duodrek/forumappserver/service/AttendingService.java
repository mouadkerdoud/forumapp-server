package com.duodrek.forumappserver.service;

import com.duodrek.forumappserver.model.Attending;
import com.duodrek.forumappserver.model.User;

import java.util.List;

public interface AttendingService {
    Attending saveAttending(Attending attending);

    void deleteAttending(Long attendingId);

    List<Attending> findAllAttendings();

    Long numberOfAttendings();
}
