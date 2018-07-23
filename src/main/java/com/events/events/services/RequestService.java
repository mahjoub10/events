package com.events.events.services;

import com.events.events.mapper.RequestMapper;
import com.events.events.models.Event;
import com.events.events.models.Request;
import com.events.events.models.RequestStatus;
import com.events.events.models.Speaker;
import com.events.events.repositories.EventRepository;
import com.events.events.repositories.RequestRepository;
import com.events.events.repositories.SpeakerRepository;
import com.events.events.web.dto.EventDto;
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
    private RequestMapper requestMapper ;


    public RequestDto addRequest(RequestDto dto) {

        long eventId = dto.getEventId();

        long speakerId = dto.getSpeakerId();

        logger.info("Speaker : {} requests event  : {}", speakerId, eventId);

        // GET THE EVENT
        Event event = eventRepository.findOneById(eventId)
                .orElseThrow(() -> new IllegalStateException("No event has been found"));

        // GET THE SPEAKER
        Speaker speaker = speakerRepository.findOneById(speakerId)
                .orElseThrow(() -> new IllegalStateException("No speaker has been found"));

        checkRequestSent(speakerId, event);

        Request request = new Request();
        request.setDate(new Date());
        request.setStatus(RequestStatus.WAITING);
        request.setDescription(dto.getDescription());
        request.setSpeaker(speaker);

        Request savedRequest = requestRepository.saveAndFlush(request);

        event.getRequests().add(savedRequest);

        eventRepository.save(event);

        return dto;

    }


    public boolean changeRequestStatus(long requestId, RequestStatus status) {

        Request request = requestRepository.findOneById(requestId)
                .orElseThrow(() -> new IllegalStateException("No request has been found"));

        request.setStatus(status);
        requestRepository.save(request);

        return true;
    }

    public List<RequestDto> findALlRequest() {

        List<Request> requests = this.requestRepository.findByStatus(RequestStatus.WAITING);

        return requests
                .stream()
                .map(requestMapper::requestToRequestDto)
                .collect(Collectors.toList());
    }

    private void checkRequestSent(long speakerId, Event event) {

        boolean requestAlwaysSent = event.getRequests()
                .stream()
                .anyMatch(r -> r.getSpeaker().getId() == speakerId);

        if (requestAlwaysSent) {
            throw new IllegalArgumentException("La demande est déja envoyé");
        }
    }
}
