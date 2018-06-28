package com.events.events.mapper;

import com.events.events.models.Speaker;
import com.events.events.web.dto.SpeakerDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * Custom Dto mapper for speakers.
 *
 */
@Mapper(componentModel = "spring")
public interface SpeakerMapper {

    /**
     * Map speaker dto to speaker domain.
     *
     * @param speakerDto
     * @return
     */
    Speaker fromSpeakerDtoToSpeaker(SpeakerDto speakerDto);

    /**
     * Map speaker domain to speaker dto.
     *
     * @param speaker
     * @return
     */
    @Mappings(
            {@Mapping(target = "roles", expression = "java(speaker.getAuthorities().stream().map(org.springframework.security.core.GrantedAuthority::getAuthority).collect(java.util.stream.Collectors.toList()))"),
                    @Mapping(target = "password", ignore = true)})
    SpeakerDto fromSpeakerToSpeakerDto(Speaker speaker);
}
