package com.events.events.services;

import com.events.events.mapper.RequestMapper;
import com.events.events.models.Event;
import com.events.events.models.Request;
import com.events.events.models.RequestStatus;
import com.events.events.models.Speaker;
import com.events.events.repositories.EventRepository;
import com.events.events.repositories.RequestRepository;
import com.events.events.repositories.SpeakerRepository;
import com.events.events.web.dto.RequestDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Request service
 */
@Service
public class RequestService {

    private Logger logger = LoggerFactory.getLogger(RequestService.class);

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private SpeakerRepository speakerRepository;

    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private UserService userService ;


    public RequestDto addRequest(RequestDto dto) {

        long eventId = dto.getEvent().getId();

        long speakerId = dto.getSpeakerId();

        logger.info("Speaker : {} requests event  : {}", speakerId, eventId);

        // GET THE EVENT
        Event event = eventRepository.findOneById(eventId)
                .orElseThrow(() -> new IllegalStateException("No event has been found"));

        // GET THE SPEAKER
        Speaker speaker = speakerRepository.findOneById(speakerId)
                .orElseThrow(() -> new IllegalStateException("No speaker has been found"));

        checkRequestSent(speakerId, eventId);

        Request request = new Request();
        request.setDate(new Date());
        request.setStatus(RequestStatus.WAITING);
        request.setDescription(dto.getDescription());
        request.setSpeaker(speaker);
        request.setEvent(event);

        Request savedRequest = requestRepository.saveAndFlush(request);

        return requestMapper.requestToRequestDto(savedRequest);

    }


    public boolean changeRequestStatus(long requestId, RequestStatus status) {

        Request request = requestRepository.findOneById(requestId)
                .orElseThrow(() -> new IllegalStateException("No request has been found"));

        request.setStatus(status);
        requestRepository.save(request);

        return true;
    }

    public List<RequestDto> findALlRequest() {

        long organizerId = userService.getCurrentUser().getId();
        List<Request> requests = this.requestRepository.findByStatusAndOrganizer(RequestStatus.WAITING, organizerId);

        return requests
                .stream()
                .map(requestMapper::requestToRequestDto)
                .collect(Collectors.toList());
    }

    private void checkRequestSent(long speakerId, long eventId) {

        boolean requestAlwaysSent = !requestRepository
                .findByEventAndSpeaker(eventId, speakerId)
                .isEmpty();

        if (requestAlwaysSent) {
            throw new IllegalArgumentException("La demande est déja envoyée");
        }
    }
}
