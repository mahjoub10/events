package com.events.events.services;

import com.events.events.mapper.OrganizerMapper;
import com.events.events.models.Authority;
import com.events.events.models.Organizer;
import com.events.events.repositories.OrganizerRepository;
import com.events.events.web.dto.OrganizerDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrganizerService {

    private Logger logger = LoggerFactory.getLogger(OrganizerService.class);

    @Autowired
    private OrganizerMapper organizerMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private OrganizerRepository organizerRepository;

    public OrganizerDto createOrganizer(OrganizerDto dto) {

        logger.info("Creating organizer");

        Organizer organizer = organizerMapper.organizerDtoToOrganizer(dto);

        String encodedPassword = this.passwordEncoder.encode(dto.getPassword());
        organizer.setPassword(encodedPassword);

        // THE ORGANIZER IS ACTIVATED BY DEFAULT
        organizer.setActivated(true);

        organizer.setAuthority(Authority.ROLE_ORGANIZER);

        Organizer savedOrganizer = organizerRepository.save(organizer);

        OrganizerDto organizerDto = organizerMapper.organizerToOrganizerDto(savedOrganizer);

        return organizerDto;
    }

    public List<OrganizerDto> getOrganizerList() {

        logger.info("Getting organizer list");

        return organizerRepository
                .findAll()
                .stream()
                .map(organizerMapper::organizerToOrganizerDto)
                .collect(Collectors.toList());
    }
}
