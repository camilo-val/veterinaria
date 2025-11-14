package com.veterinaria.authentication.infrastructure.drivenadapter.restconsumer.mapper;

import com.veterinaria.authentication.domain.model.User;
import com.veterinaria.authentication.infrastructure.drivenadapter.restconsumer.dto.UserResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapperAdapter {


    default User toDomain(UserResponse userResponse){
        return new User(userResponse.getUsername(), userResponse.getPassword(), userResponse.getRole(), userResponse.getStatus());

    }

}
