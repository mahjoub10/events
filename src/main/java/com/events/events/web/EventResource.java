package com.events.events.web;

import com.events.events.services.EventService;
import com.events.events.web.dto.EventDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Event controller that groups the WS endpoints.
 */
@RequestMapping("/api/events")
@RestController
public class EventResource {

    private Logger logger = LoggerFactory.getLogger(EventResource.class);

    @Autowired
    private EventService eventService ;

    /**
     * Create new event.
     *
     * @param event
     */
    @PostMapping("/create")
    @Secured("ROLE_ORGANIZER")
    public  ResponseEntity<EventDto> createEvent(@RequestBody EventDto event) {
        logger.info("Request to save new Event : {}",event);
        EventDto result = this.eventService.createEvent(event);
        return  ResponseEntity.ok(result);
    }

    /**
     * Get back the list of all available events.
     *
     * @return
     */
    @GetMapping("/all")
    public ResponseEntity<List<EventDto>> getAllEvents() {

        logger.info("Request to get all event");
        List<EventDto> result = this.eventService.getAllEvent();
        return  ResponseEntity.ok(result);

    }

    @GetMapping("/get")
    public  ResponseEntity<EventDto> getEventDetail(@RequestParam("eventId") long eventId) {

        logger.info("Request to get event detail : '{}' ", eventId );

        EventDto result = eventService.findEventById(eventId) ;
        return  ResponseEntity.ok(result);
    }

    /**
     * Get  the list of all available events for a given speaker.
     *
     * @return
     */
    @GetMapping("/speaker/all")
    public ResponseEntity<List<EventDto>> getEventBySpeaker(@RequestParam("speakerId") long speakerId) {

        logger.info("Request to get all event");
        List<EventDto> result = this.eventService.getEventBySpeaker(speakerId);
        return  ResponseEntity.ok(result);

    }

}
