package com.events.events.services;

import com.events.events.mapper.AttendeeMapper;
import com.events.events.mapper.EventMapper;
import com.events.events.models.Attendee;
import com.events.events.models.Authority;
import com.events.events.models.Event;
import com.events.events.models.User;
import com.events.events.repositories.AttendeeRepository;
import com.events.events.web.dto.AttendeeDto;
import com.events.events.web.dto.EventDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    private PasswordEncoder endcoder;

    @Autowired
    private UserService userService ;

    @Autowired
    private EventMapper eventMapper ;

    /**
     * Create new attendee, called for subscription.
     *
     * @param dto
     * @return
     */
    public AttendeeDto createNewAttendee(AttendeeDto dto) {

        logger.info("Creation new attendee");


        Attendee attendee = mapper.fromAttendeeDtoToAttendee(dto);

         String encodedPassword = this.endcoder.encode(dto.getPassword());
         attendee.setPassword(encodedPassword);

         // THE ATTENDEE IS ACTIVATED BY DEFAULT
         attendee.setActivated(true);

         attendee.setAuthority(Authority.ROLE_ATTENDEE);

        // SAVE TO DATABASE
        attendeeRepository.save(attendee);

        return  this.mapper.fromAttendeeToAttendeeDto(attendee);

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
     * @param idEvent
     */
    public EventDto subscribeToEvents(long idEvent) {

        logger.info("Subscribe the attendee to the event : {}",  idEvent);

        User currentUser = userService.getCurrentUser();

        Attendee attendee = this.getAttendeeById(currentUser.getId());

        Event event = this.eventService.getEventById(idEvent);

        // ADD ATTENDEE TO EVENT
        event.getAttendees().add(attendee);

        // SAVE CHANGES
        this.eventService.saveEvent(event);

        return  eventMapper.eventToEventDto(event);

    }

    /**
     * Remove the attendee from event's attendees.
     *
     * @param idEvent
     */
    public EventDto  unsubscribeFromEvent(long idEvent) {

        logger.info("unsubscribe the attendee :  from the event : {} ",  idEvent);

        User currentUser = userService.getCurrentUser();

        Event event = this.eventService.getEventById(idEvent);

        // REMOVE THE ATTENDEE IF FOUND
        event.getAttendees().removeIf(a -> a.getId() == currentUser.getId());

        // SAVE CHANGE
        this.eventService.saveEvent(event);

        return  eventMapper.eventToEventDto(event);


    }


    // FIND ATTENDEE
    private Attendee getAttendeeById(Long id) {
        return attendeeRepository.findOneById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("No attendee has been found using id : %", id)));
    }


}
