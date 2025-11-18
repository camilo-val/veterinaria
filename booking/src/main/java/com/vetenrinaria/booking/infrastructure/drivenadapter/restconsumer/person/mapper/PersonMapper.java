package com.vetenrinaria.booking.infrastructure.drivenadapter.restconsumer.person.mapper;

import com.vetenrinaria.booking.domain.model.person.Person;
import com.vetenrinaria.booking.infrastructure.drivenadapter.restconsumer.person.dto.PersonResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonMapper {
    default Person toDomain(PersonResponse personResponse) {
        return new Person(personResponse.getId(),
                personResponse.getName(),
                personResponse.getEmail(),
                personResponse.getPhone(),
                personResponse.getAddress(),
                personResponse.getUser().getId());
    }
}
