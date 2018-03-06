package com.events.events.mapper;

import com.events.events.models.Event;
import com.events.events.web.dto.EventDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EventMapper {

    Event eventDtoToEvent(EventDto event);

    EventDto eventToEventDto(Event event);
}
