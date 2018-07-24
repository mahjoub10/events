package com.events.events.mapper;

import com.events.events.models.Organizer;
import com.events.events.web.dto.OrganizerDto;
import org.mapstruct.Mapper;

/**
 * Custom DTO mapper for organizer .
 */
@Mapper(componentModel = "spring")
public interface OrganizerMapper {

    /**
     * Map organizer dto  to organizer domain.
     *
     * @param dto
     * @return
     */
    Organizer organizerDtoToOrganizer(OrganizerDto dto);

    /**
     * Map organizer domain to organizer dto
     *
     * @param organizer
     * @return
     */
    OrganizerDto organizerToOrganizerDto(Organizer organizer);
}
