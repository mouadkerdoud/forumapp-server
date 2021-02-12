package com.duodrek.forumappserver.service;


import com.duodrek.forumappserver.model.Event;
import com.duodrek.forumappserver.model.User;
import com.duodrek.forumappserver.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class EventServiceImplementation implements EventService {

    @Autowired
    EventRepository eventRepository;

    @Override
    public Event saveEvent(Event event){
        return eventRepository.save(event);
    }

    @Override
    public Event updateEvent(Event event){
        Event existingEvent = eventRepository.findById(event.getEventId()).orElse(null);
        existingEvent.setEventName(event.getEventName());
        existingEvent.setEventDescription(event.getEventDescription());
        existingEvent.setStartDate(event.getStartDate());
        existingEvent.setFinishDate(event.getFinishDate());
        return eventRepository.save(existingEvent);
    }

    @Override
    public void deleteEvent(Long eventId){
        eventRepository.deleteById(eventId);
    }

    @Override
    public Event findEventById(Long eventId){
        return eventRepository.findById(eventId).orElse(null);
    }

    @Override
    public List<Event> getAllEvents(){
        return eventRepository.findAll();
    }

    @Override
    public Long numberOfEvents(){
        return eventRepository.count();
    }



}
