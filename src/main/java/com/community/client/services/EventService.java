package com.community.client.services;

import com.community.client.models.Event;
import com.community.client.repositories.EventRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.Set;

@Service
public class EventService {

    private EventRepository eventRepository;
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Event saveEvent(Event event){
        return eventRepository.save(event);
    }

    public Set<Event> getAllEvents(){
        return eventRepository.findAll();
    }

    public Set<Event> getEventSearchResults(String keyword) {
        return eventRepository.findByKeyword(keyword);
    }

    public Event getEventById(Long id){
        Optional<Event> eventOptional = eventRepository.findEventById(id);
        return eventOptional.orElse(null);
    }
}
