package com.events.events.mapper;

import com.events.events.models.Event;
import com.events.events.web.dto.EventDto;
import net.bytebuddy.asm.Advice;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Custom DTO mapper for event .
 */
@Mapper(componentModel = "spring")
public abstract class EventMapper {

    @Autowired
    protected OrganizerMapper organizerMapper;

    @Autowired
    protected  RequestMapper requestMapper ;

    /**
     * Map event dto  to event domain.
     *
     * @param event
     * @return
     */
    public abstract  Event eventDtoToEvent(EventDto event);

    /**
     * Map Event domain to event dto
     *
     * @param event
     * @return
     */
    @Mappings
    ({@Mapping(target = "organizer", expression = "java(organizerMapper.organizerToOrganizerDto(event.getOrganizer()))"),
            @Mapping(target = "requests", expression = "java(event.getRequests().stream().map(requestMapper::requestToRequestDto).collect(java.util.stream.Collectors.toSet()))")
    })
    public abstract   EventDto eventToEventDto(Event event) ;

}
