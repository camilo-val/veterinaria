package com.vetenrinaria.person.infrastructure.adapter.restconsumer.mapper;

import com.vetenrinaria.person.domain.model.user.User;
import com.vetenrinaria.person.infrastructure.adapter.restconsumer.dto.UserRequest;
import com.vetenrinaria.person.infrastructure.adapter.restconsumer.dto.UserResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserDtoMapper {

    UserRequest toRequest(User user);
    User toDomain(UserResponse response);
    List<User> toDomainList(List<UserResponse> responseList);

    User toDomain(com.vetenrinaria.person.infrastructure.entrypoint.restconsumer.dto.UserRequest user);
}
