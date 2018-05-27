package com.events.events.services;

import com.events.events.mapper.EventMapper;
import com.events.events.models.Event;
import com.events.events.repositories.EventRepository;
import com.events.events.web.dto.EventDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Event operations.
 */
@Service
public class EventService {

    private Logger logger = LoggerFactory.getLogger(EventService.class);

    @Autowired
    private EventMapper eventMapper;

    @Autowired
    private EventRepository eventRepository;

    /**
     * Create new Event.
     * @param event
     */
    public void createEvent(EventDto event) {

        logger.info("Save new event");
        Event eventToSave = eventMapper.eventDtoToEvent(event);
        this.saveEvent(eventToSave);

    }


    /**
     * Get event list
     * @return
     */
    public List<EventDto> getAllEvent() {

        return eventRepository.findAll()
                .stream()/**/
                .map(eventMapper::eventToEventDto)
                .collect(Collectors.toList());
    }

    /**
     * Find event by id.
     * @param id
     * @return
     */
    public Event getEventById(Long id) {
        return eventRepository.findOneById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("No event has been found using id : %", id)));
    }

    /**
     * Save changes .
     *
     * @param event
     */
    public  void saveEvent(Event event) {

        this.eventRepository.save(event);
    }


}
