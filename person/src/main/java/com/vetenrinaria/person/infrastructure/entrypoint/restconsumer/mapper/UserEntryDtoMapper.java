package com.vetenrinaria.person.infrastructure.entrypoint.restconsumer.mapper;

import com.vetenrinaria.person.domain.model.user.User;
import com.vetenrinaria.person.infrastructure.entrypoint.restconsumer.dto.UserRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserEntryDtoMapper {

    UserRequest toRequest(User user);
    User toDomain(UserRequest response);
}
