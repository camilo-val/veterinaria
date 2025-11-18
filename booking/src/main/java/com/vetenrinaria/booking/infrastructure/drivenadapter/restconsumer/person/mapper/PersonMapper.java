package com.vetenrinaria.booking.infrastructure.drivenadapter.restconsumer.person.mapper;

import com.vetenrinaria.booking.domain.model.person.Person;
import com.vetenrinaria.booking.domain.model.person.PersonWithUser;
import com.vetenrinaria.booking.domain.model.person.user.User;
import com.vetenrinaria.booking.infrastructure.drivenadapter.restconsumer.person.dto.PersonResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonMapper {
    default PersonWithUser toDomain(PersonResponse personResponse) {
        return new PersonWithUser(new Person(personResponse.getId(),
                personResponse.getName(),
                personResponse.getEmail(),
                personResponse.getPhone(),
                personResponse.getAddress(),
                personResponse.getUser().getId()),
                new User(personResponse.getUser().getId(),
                        personResponse.getUser().getUsername(),
                        personResponse.getUser().getPassword(),
                        personResponse.getUser().getRole(),
                        personResponse.getUser().getStatus(),
                        personResponse.getUser().getCreatedAt(),
                        personResponse.getUser().getUpdateAt())
                );
    }
}
