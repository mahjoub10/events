package com.events.events.mapper;

import com.events.events.models.Attendee;
import com.events.events.web.dto.AttendeeDto;
import org.mapstruct.Mapper;

/**
 * Custom Dto mapper for attendee.
 */
@Mapper(componentModel = "spring")
public interface AttendeeMapper {

    /**
     * Map attendee domain dto to attendee.
     *
     * @param attendeeDto
     * @return
     */
    Attendee fromAttendeeDtoToAttendee(AttendeeDto attendeeDto) ;

    /**
     * Map attendee domain to attendee dto.
     *
     * @param attendee
     * @return
     */
    AttendeeDto fromAttendeeToAttendeeDto(Attendee attendee);


}
