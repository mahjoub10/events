package com.events.events.web;

import com.events.events.services.SpeakerService;
import com.events.events.web.dto.SpeakerDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/speaker")
public class SpeakerResource {

    private Logger logger = LoggerFactory.getLogger(SpeakerResource.class);

    @Autowired
    private SpeakerService speakerService;

    /**
     * Create new speaker.
     *
     * @param speakerDto
     */
    @PostMapping("/create")
    public  void createSpeaker(@RequestBody SpeakerDto speakerDto) {

        logger.info("WS call to create new speaker");

        this.speakerService.createSpeaker(speakerDto);
    }

    /**
     * Deactivate the target speaker.
     *
     * @param id
     */
    @PutMapping("/deactivate")
    public void deactivateSpeaker(@RequestParam("id") long id){

        logger.info("WS call to deactivate the speaker : {} ",id);

        this.speakerService.deactivateSpeaker(id);
    }

    /**
     * Delete the speaker.
     *
     * @param id
     */
    @DeleteMapping("/delete")
    public  void deleteSpeaker(@RequestParam("id") long id) {

        logger.info("WS call to delete speaker : {} ", id);

        this.speakerService.deleteSpeaker(id);
    }


    /**
     * Associate speaker to event.
     *
     * @param idSpeaker
     * @param idEvent
     */
    @GetMapping("/event/associate")
    public  void associateSpeakerToEvent(@RequestParam("idSpeaker") long idSpeaker, @RequestParam("idEvent") long idEvent) {

        logger.info("WS call to associate the speaker : {} to the event : {}", idSpeaker, idEvent);

        this.speakerService.associateToEvent(idSpeaker, idEvent);
    }


    /**
     * Remove speaker from event.
     *
     * @param idSpeaker
     * @param idEvent
     */
    @GetMapping("/event/remove")
    public  void removeSpeakerFromEvent(@RequestParam("idSpeaker") long idSpeaker, @RequestParam("idEvent") long idEvent){

        logger.info("WS call to remove the speaker : {} from the event : {} ", idSpeaker, idEvent);

        this.speakerService.removeFromEvent(idSpeaker, idEvent);
    }
}
