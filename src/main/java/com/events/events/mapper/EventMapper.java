package com.events.events.mapper;

import com.events.events.models.Event;
import com.events.events.web.dto.EventDto;
import org.mapstruct.Mapper;

/**
 * Custom DTO mapper for event .
 */
@Mapper(componentModel = "spring")
public interface EventMapper {

    /**
     * Map event dto  to event domain.
     *
     * @param event
     * @return
     */
    Event eventDtoToEvent(EventDto event);

    /**
     * Map Event domain to event dto
     * @param event
     * @return
     */
    EventDto eventToEventDto(Event event);
}
