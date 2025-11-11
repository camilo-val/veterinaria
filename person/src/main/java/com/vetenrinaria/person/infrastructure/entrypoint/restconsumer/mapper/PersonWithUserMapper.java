package com.vetenrinaria.person.infrastructure.entrypoint.restconsumer.mapper;

import com.vetenrinaria.person.domain.model.person.Person;
import com.vetenrinaria.person.domain.model.person.PersonWithUser;
import com.vetenrinaria.person.domain.model.user.User;
import com.vetenrinaria.person.infrastructure.adapter.restconsumer.mapper.UserDtoMapper;
import com.vetenrinaria.person.infrastructure.entrypoint.restconsumer.dto.PersonRequest;
import com.vetenrinaria.person.infrastructure.entrypoint.restconsumer.dto.PersonResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ObjectFactory;

@Mapper(componentModel = "spring", uses = {UserDtoMapper.class})
public interface PersonWithUserMapper {

    @Mapping(target = "person", ignore = true)
    @Mapping(target = "user", source = "user")
    PersonWithUser toDomain(PersonRequest request);


    @Mapping(target = "id", source = "person.id")
    @Mapping(target = "name", source = "person.name")
    @Mapping(target = "email", source = "person.email")
    @Mapping(target = "phone", source = "person.phone")
    @Mapping(target = "address", source = "person.address")
    @Mapping(target = "user", source = "user")
    PersonResponse toResponse(PersonWithUser personWithUser);

    @ObjectFactory
    default PersonWithUser create(PersonRequest request, UserDtoMapper userMapper) {
        Person person = Person.fromPersistence(
                request.getId(),
                request.getName(),
                request.getEmail(),
                request.getPhone(),
                request.getAddress(),
                request.getUser() != null ? request.getUser().getId() : null
        );

        User user = userMapper.toDomain(request.getUser());
        return new PersonWithUser(person, user);
    }
}
