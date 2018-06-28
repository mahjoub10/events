package com.events.events.mapper;

import com.events.events.models.User;
import com.events.events.web.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mappings(
            {@Mapping(target = "roles", expression = "java(user.getAuthorities().stream().map(org.springframework.security.core.GrantedAuthority::getAuthority).collect(java.util.stream.Collectors.toList()))"),
            @Mapping(target = "password", ignore = true)})
    UserDto fromUserToUserDto(User user);
}
