package com.events.events.web;

import com.events.events.services.AttendeeService;
import com.events.events.web.dto.AttendeeDto;
import com.events.events.web.dto.EventDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/attendee")
@RestController
public class AttendeeResource {

    private Logger logger = LoggerFactory.getLogger(AttendeeResource.class);

    @Autowired
    private AttendeeService attendeeService;

    /**
     * Attendee new subscription.
     *
     * @param attendeeDto
     */
    @PostMapping("/subscribe")
    public ResponseEntity<AttendeeDto> createAttendee(@RequestBody AttendeeDto attendeeDto) {

        logger.info("WS call to add new attendee");
        AttendeeDto result = this.attendeeService.createNewAttendee(attendeeDto);
        return  ResponseEntity.ok(result);
    }

    /**
     * Deactivate the attendee.
     *
     * @param id
     */
    @PutMapping("/deactivate")
    public void deactivateAttendee(@RequestParam("id") long id){

        logger.info("WS call to deactivate the attendee : {}", id);
        this.attendeeService.deactivateAttendee(id);
    }

    /**
     * delete the attendee.
     * @param id
     */
    @DeleteMapping("/delete")
    public void deleteAttendee(@RequestParam("id") long id) {

        logger.info("WS call to delete the attendee : {} ",id);
        this.attendeeService.deleteAttendee(id);
    }

    /**
     * Subscribe to event.
     *
     * @param idEvent
     */
    @GetMapping("/event/subscribe")
    public  ResponseEntity<EventDto>  subscribeToEvent(@RequestParam("idEvent") long idEvent) {

        logger.info("WS call to subscribe attendee  to event : {}", idEvent);
        EventDto result = this.attendeeService.subscribeToEvents(idEvent);

        return  ResponseEntity.ok(result);
    }

    /**
     *
     * @param idEvent
     * @return
     */
    @GetMapping("/event/unSubscribe")
    public  ResponseEntity<EventDto>  unsubscribeFromEvent( @RequestParam("idEvent") long idEvent) {

        logger.info("WS call to unsubscribe  attendee :  from  event : {}", idEvent);
        EventDto result = this.attendeeService.unsubscribeFromEvent(idEvent);

        return  ResponseEntity.ok(result);
    }
}
