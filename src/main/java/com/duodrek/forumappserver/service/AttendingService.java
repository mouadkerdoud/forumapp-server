package com.duodrek.forumappserver.service;

import com.duodrek.forumappserver.model.Attending;

import java.util.List;

public interface AttendingService {
    Attending saveAttending(Attending attending);

    List<Attending> findAllAttendings();
}
