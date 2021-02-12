package com.duodrek.forumappserver.service;

import com.duodrek.forumappserver.model.Attending;
import com.duodrek.forumappserver.repository.AttendingRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class AttendingServiceImplementation implements AttendingService {

    @Autowired
    AttendingRepository attendingRepository;

    @Override
    public Attending saveAttending(Attending attending){
        return attendingRepository.save(attending);
    }
}
