package com.events.events.services;

import com.events.events.mapper.AttendeeMapper;
import com.events.events.models.Attendee;
import com.events.events.models.Event;
import com.events.events.repositories.AttendeeRepository;
import com.events.events.web.dto.AttendeeDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AttendeeService {

    private Logger logger = LoggerFactory.getLogger(AttendeeService.class);


    @Autowired
    private AttendeeMapper mapper;

    @Autowired
    private AttendeeRepository attendeeRepository;

    @Autowired
    private EventService eventService;

    /**
     * Create new attendee, called for subscription.
     *
     * @param dto
     * @return
     */
    public void createNewAttendee(AttendeeDto dto) {

        logger.info("Creation new attendee");

        Objects.requireNonNull(dto);
        Attendee attendee = mapper.fromAttendeeDtoToAttendee(dto);

        // SAVE TO DATABASE
        attendeeRepository.save(attendee);

    }

    /**
     * Deactivated the target attendee.
     *
     * @param id
     */
    public void deactivateAttendee(long id) {

        logger.info("Deactivating the attendee : {} ", id);
        Attendee attendee = getAttendeeById(id);

        // SET ACTIVATED TO FALSE
        attendee.setActivated(false);

        // SAVE CHANGES
        this.attendeeRepository.save(attendee);

    }

    /**
     * Delete the target attendee.
     *
     * @param id
     */
    public void deleteAttendee(long id) {

        logger.info("Deleting the attendee : {} ", id);

        Attendee attendee = getAttendeeById(id);

        // SET DELETED TO FALSE
        attendee.setDeleted(true);

        // UPDATE ATTENDEE
        this.attendeeRepository.save(attendee);
    }

    /**
     * Add attendee  to event's attendees.
     *
     * @param idAttendee
     * @param idEvent
     */
    public void subscribeToEvents(long idAttendee, long idEvent) {

        logger.info("Subscribe the attendee : {} to the event : {}", idAttendee, idEvent);

        Attendee attendee = this.getAttendeeById(idAttendee);

        Event event = this.eventService.getEventById(idEvent);

        // ADD ATTENDEE TO EVENT
        event.getAttendees().add(attendee);

        // SAVE CHANGES
        this.eventService.saveEvent(event);

    }

    /**
     * Remove the attendee from event's attendees.
     *
     * @param idAttendee
     * @param idEvent
     */
    public void unsubscribeFromEvent(long idAttendee, long idEvent) {

        logger.info("unsubscribe the attendee : {} from the event : {} ", idAttendee, idEvent);
        Event event = this.eventService.getEventById(idEvent);

        // REMOVE THE ATTENDEE IF FOUND
        event.getAttendees().removeIf(a -> a.getId() == idAttendee);

        // SAVE CHANGE
        this.eventService.saveEvent(event);

    }


    // FIND ATTENDEE
    private Attendee getAttendeeById(Long id) {
        return attendeeRepository.findOneById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("No attendee has been found using id : %", id)));
    }


}
