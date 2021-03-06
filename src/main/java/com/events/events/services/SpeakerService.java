package com.events.events.services;

import com.events.events.mapper.SpeakerMapper;
import com.events.events.models.Authority;
import com.events.events.models.Event;
import com.events.events.models.Speaker;
import com.events.events.repositories.SpeakerRepository;
import com.events.events.web.dto.SpeakerDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Speaker service.
 */
@Service
public class SpeakerService {

    private Logger logger = LoggerFactory.getLogger(SpeakerService.class);

    @Autowired
    private SpeakerRepository speakerRepository;

    @Autowired
    private SpeakerMapper mapper;

    @Autowired
    private EventService eventService;

    @Autowired
    private PasswordEncoder endcoder;

    /**
     * Create new speaker.
     *
     * @param dto
     */
    public SpeakerDto createSpeaker(SpeakerDto dto) {

        logger.info("Creating new speaker ");
        Speaker speaker = mapper.fromSpeakerDtoToSpeaker(dto);

        String encodedPassword = this.endcoder.encode(dto.getPassword());
        speaker.setPassword(encodedPassword);

        // THE SPEAKER IS ACTIVATED BY DEFAULT
        speaker.setActivated(true);

        speaker.setAuthority(Authority.ROLE_SPEAKER);

        // SAVE TO DATABASE
        speakerRepository.save(speaker);

        return this.mapper.fromSpeakerToSpeakerDto(speaker);


    }

    /**
     * Deactivate the target speaker.
     *
     * @param id
     */
    public void deactivateSpeaker(long id) {

        logger.info("Deactivating the speaker : {} ", id);
        Speaker speaker = this.getSpeakerById(id);

        // SET ACTIVATE TO FALSE
        speaker.setActivated(false);

        // SAVE CHANGES
        this.speakerRepository.save(speaker);

    }

    /**
     * Delete speaker.
     *
     * @param id
     */
    public void deleteSpeaker(long id) {

        logger.info("Deleting the speaker : {}", id);
        Speaker speaker = this.getSpeakerById(id);

        // SET ACTIVATE TO FALSE
        speaker.setDeleted(true);

        // SAVE CHANGES
        this.speakerRepository.save(speaker);
    }

    /**
     * Associate speaker to event.
     *
     * @param idSpeaker
     * @param idEvent
     */
    public void associateToEvent(long idSpeaker, long idEvent) {

        logger.info("Associating the event : {} to the speaker : {}", idEvent, idSpeaker);

        Speaker speaker = this.getSpeakerById(idSpeaker);
        Event event = this.eventService.getEventById(idEvent);
        event.getSpeakers().add(speaker);

        // SAVE CHANGES
        this.eventService.saveEvent(event);

    }


    public List<SpeakerDto> getSpeakers() {

        return speakerRepository
                .findAll()
                .stream()
                .map(mapper::fromSpeakerToSpeakerDto)
                .collect(Collectors.toList());
    }

    public void removeFromEvent(long idSpeaker, long idEvent) {

        logger.info("Removing the speaker : {} from event : {} ", idSpeaker, idEvent);

        Event event = this.eventService.getEventById(idEvent);

        // REMOVE SPEAKER IF FOUND.
        event.getSpeakers().removeIf(s -> s.getId() == idSpeaker);

        // SAVE CHANGES
        this.eventService.saveEvent(event);
    }

    // FIND ATTENDEE
    private Speaker getSpeakerById(Long id) {
        return speakerRepository.findOneById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("No speaker has been found using id : %", id)));
    }


}
