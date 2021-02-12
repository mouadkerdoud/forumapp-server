package com.duodrek.forumappserver.service;

import com.duodrek.forumappserver.model.Event;
import com.duodrek.forumappserver.model.User;

import java.util.List;

public interface EventService {
    Event saveEvent(Event event);

    Event updateEvent(Event event);

    void deleteEvent(Long eventId);

    Event findEventById(Long eventId);

    List<Event> getAllEvents();

    Long numberOfEvents();


}
