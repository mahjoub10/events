package com.events.events.mapper;

import com.events.events.models.Request;
import com.events.events.web.dto.RequestDto;
import org.mapstruct.Mapper;

/**
 * Custom DTO mapper for request .
 */
@Mapper(componentModel = "spring")
public interface RequestMapper {

    /**
     * Map Request domain to event dto
     * @param request
     * @return
     */
    RequestDto requestToRequestDto(Request request);
}
