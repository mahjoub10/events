package com.events.events.web;

import com.events.events.services.AttendeeService;
import com.events.events.web.dto.AttendeeDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void createAttendee(@RequestBody AttendeeDto attendeeDto) {

        logger.info("WS call to add new attendee");
        this.attendeeService.createNewAttendee(attendeeDto);
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
     * @param idAttendee
     * @param idEvent
     */
    @GetMapping("/event/subscribe")
    public  void subscribeToEvent(@RequestParam("idAttendee") long idAttendee, @RequestParam("idEvent") long idEvent) {

        logger.info("sWS call to subscribe attendee : {} to event : {}", idAttendee, idEvent);
        this.attendeeService.subscribeToEvents(idAttendee, idEvent);
    }


    public  void unsubscribeFromEvent(@RequestParam("idAttendee") Long idAttendee, @RequestParam("idEvent") long idEvent) {

        logger.info("sWS call to unsubscribe  attendee : {} from  event : {}", idAttendee, idEvent);
        this.attendeeService.unsubscribeFromEvent(idAttendee, idEvent);
    }
}
