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


    default UserResponse toResponse(User user){
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getRole(),
                user.getStatus(),
                user.getCreatedAt(),
                user.getUpdateAt()
        );
    }

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