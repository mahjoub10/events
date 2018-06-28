package com.events.events.mapper;

import com.events.events.models.Attendee;
import com.events.events.web.dto.AttendeeDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

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
    Attendee fromAttendeeDtoToAttendee(AttendeeDto attendeeDto);

    /**
     * Map attendee domain to attendee dto.
     *
     * @param attendee
     * @return
     */
    @Mappings(
            {@Mapping(target = "roles", expression = "java(attendee.getAuthorities().stream().map(org.springframework.security.core.GrantedAuthority::getAuthority).collect(java.util.stream.Collectors.toList()))"),
                    @Mapping(target = "password", ignore = true)})
    AttendeeDto fromAttendeeToAttendeeDto(Attendee attendee);


}
