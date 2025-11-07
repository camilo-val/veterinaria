package com.vetenrinaria.user.infrastructure.entrypoint.mapper;

import com.vetenrinaria.user.domain.model.User;
import com.vetenrinaria.user.infrastructure.entrypoint.dto.UserRequest;
import com.vetenrinaria.user.infrastructure.entrypoint.dto.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ObjectFactory;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserDtoMapper {

    UserDtoMapper INSTANCE = Mappers.getMapper(UserDtoMapper.class);

    // Domain ↔ DTO
    UserRequest toRequest(User user);
    User toDomain(UserRequest request);

    // Request → Response
    UserResponse toResponse(User request);

    @ObjectFactory
    default User createUser(UserRequest request) {
        return User.createUser(
                request.getId(),
                request.getUsername(),
                request.getPassword(),
                request.getRole()
        );
    }
}
