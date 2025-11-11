package com.vetenrinaria.user.infrastructure.entrypoint.mapper;

import com.vetenrinaria.user.domain.model.User;
import com.vetenrinaria.user.infrastructure.entrypoint.dto.UserRequest;
import com.vetenrinaria.user.infrastructure.entrypoint.dto.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ObjectFactory;

@Mapper(componentModel = "spring")
public interface UserDtoMapper {

    // Request → Domain
    User toDomain(UserRequest request);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "username", source = "username")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "role", source = "role")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updateAt", source = "updateAt")
    UserResponse toResponse(User user);

    @ObjectFactory
    default User createUser(UserRequest request) {
        System.out.println("ETRAASASSAS");
        return User.createUser(
                request.getId(),
                request.getUsername(),
                request.getPassword(),
                request.getRole(),
                request.getStatus(),
                request.getCreatedAt(),
                request.getUpdateAt()
        );
    }
}