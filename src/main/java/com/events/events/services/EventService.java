package com.events.events.services;

import com.events.events.mapper.EventMapper;
import com.events.events.models.Event;
import com.events.events.models.Organizer;
import com.events.events.models.Speaker;
import com.events.events.models.User;
import com.events.events.repositories.EventRepository;
import com.events.events.repositories.SpeakerRepository;
import com.events.events.web.dto.EventDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
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

    @Autowired
    private SpeakerRepository speakerRepository;

    @Autowired
    private UserService userService;

    /**
     * Create new Event.
     *
     * @param event
     */
    public EventDto createEvent(EventDto event) {

        logger.info("Save new event");
        Event eventToSave = eventMapper.eventDtoToEvent(event);

//        Set<Speaker> speakers = event
//                .getSpeakers()
//                .stream()
//                .map(sp -> speakerRepository.findOneById(sp.getId()))
//                .map(Optional::get)
//                .collect(Collectors.toSet());
//        eventToSave.setSpeakers(speakers);

        User currentUser = userService.getCurrentUser();
        Organizer organizer = (Organizer) currentUser;
        eventToSave.setOrganizer(organizer);

        Event savedEvent = this.saveEvent(eventToSave);

        return eventMapper.eventToEventDto(savedEvent);

    }


    /**
     * Get event list
     *
     * @return
     */
    public List<EventDto> getAllEvent() {

        return eventRepository.findEventByDate()
                .stream()/**/
                .map(eventMapper::eventToEventDto)
                .collect(Collectors.toList());
    }

    /**
     * Get event list
     *
     * @return
     */
    public List<EventDto> getEventBySpeaker(long speakerId) {

        return eventRepository.findEventBySpeakersIdIn(speakerId)
                .stream()/**/
                .map(eventMapper::eventToEventDto)
                .collect(Collectors.toList());
    }


    /**
     * Find event by id.
     *
     * @param id
     * @return
     */
    public EventDto findEventById(Long id) {

        Event event = this.getEventById(id);
        return eventMapper.eventToEventDto(event);
    }

    /**
     * Find event by id.
     *
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
    public Event saveEvent(Event event) {

        return this.eventRepository.save(event);
    }


}
