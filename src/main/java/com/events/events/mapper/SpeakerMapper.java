package com.events.events.mapper;

import com.events.events.models.Speaker;
import com.events.events.web.dto.SpeakerDto;
import org.mapstruct.Mapper;

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
    SpeakerDto fromSpeakerDtoToSpeaker(Speaker speaker);
}
