package com.events.events.mapper;

import com.events.events.models.Request;
import com.events.events.web.dto.RequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * Custom DTO mapper for request .
 */
@Mapper(componentModel = "spring")
public interface RequestMapper {

    /**
     * Map Request domain to event dto
     *
     * @param request
     * @return
     */
    @Mappings
            ({@Mapping(target = "eventId", source = "request.event.id"),
                    @Mapping(target = "speakerId", source = "request.speaker.id")})
    RequestDto requestToRequestDto(Request request);
}
