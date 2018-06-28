package com.events.events.web;

import com.events.events.services.SpeakerService;
import com.events.events.web.dto.SpeakerDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/admin")
@Secured("ROLE_ADMIN")
public class AdminResource {

    private Logger logger = LoggerFactory.getLogger(AdminResource.class);

    @Autowired
    private SpeakerService speakerService;

    /**
     * Get speakers list
     * @return
     */
    @GetMapping("/speaker/list")
    public ResponseEntity<List<SpeakerDto>> getSpeakersList() {

        logger.info("WS get speakers list");
        List<SpeakerDto> result = speakerService.getSpeakers();
        return ResponseEntity.ok(result);

    }


}
