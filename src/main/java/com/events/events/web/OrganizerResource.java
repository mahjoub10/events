package com.events.events.web;

import com.events.events.services.OrganizerService;
import com.events.events.web.dto.OrganizerDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Organizer  controller that groups the WS endpoints.
 */
@RequestMapping("/api/organizer")
@RestController
public class OrganizerResource {

    private Logger logger = LoggerFactory.getLogger(OrganizerResource.class);

    @Autowired
    private OrganizerService organizerService;

    /**
     * Create new organizer.
     *
     * @param dto
     */
    @PostMapping("/create")
    public ResponseEntity<OrganizerDto> createOrganizer(@RequestBody OrganizerDto dto) {

        logger.info("WS to create organizer");
        OrganizerDto result = this.organizerService.createOrganizer(dto);

        return ResponseEntity.ok(result);
    }

    @GetMapping("/list")
    public ResponseEntity<List<OrganizerDto>> getOrganizers() {

        logger.info("WS to get the list of organizer");
        List<OrganizerDto> organizers = this.organizerService.getOrganizerList();

        return ResponseEntity.ok(organizers);

    }
}
