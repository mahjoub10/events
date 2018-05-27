package com.events.events.web;

import com.events.events.services.EventService;
import com.events.events.web.dto.EventDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public  void createEvent(@RequestBody EventDto event) {
        logger.info("Request to save new Event : {}",event);
        this.eventService.createEvent(event);
    }

    /**
     * Get back the list of all available events.
     *
     * @return
     */
    @GetMapping("/all")
    public ResponseEntity<List<EventDto>> getAllEvents() {

        logger.info("Request to get alla event");
        List<EventDto> result = this.eventService.getAllEvent();
        return  ResponseEntity.ok(result);

    }
}
