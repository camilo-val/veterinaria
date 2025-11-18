package com.vetenrinaria.pet.infrastructure.drivenadapter.restconsumer.mapper;

import com.vetenrinaria.pet.domain.model.person.Person;
import com.vetenrinaria.pet.domain.model.person.PersonWithUser;
import com.vetenrinaria.pet.domain.model.person.user.User;
import com.vetenrinaria.pet.infrastructure.drivenadapter.restconsumer.dto.PersonResponse;
import org.mapstruct.Mapper;

import java.util.Date;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    default Person toDomain(PersonResponse personResponse) {
        return new Person(
                personResponse.getId(),
                personResponse.getName(),
                personResponse.getEmail(),
                personResponse.getPhone(),
                personResponse.getAddress(),
                personResponse.getUser().getId()
        );
    }

    default PersonWithUser toDomainPerson(PersonResponse personResponse) {
        System.out.println("entre mapper adapter"+ new Date());
        return new PersonWithUser(
                new Person(
                        personResponse.getId(),
                        personResponse.getName(),
                        personResponse.getEmail(),
                        personResponse.getPhone(),
                        personResponse.getAddress(),
                        personResponse.getUser().getId()
                ),
                new User(
                        personResponse.getUser().getId(),
                        personResponse.getUser().getUsername(),
                        personResponse.getUser().getPassword(),
                        personResponse.getUser().getRole(),
                        personResponse.getUser().getStatus(),
                        personResponse.getUser().getCreatedAt(),
                        personResponse.getUser().getUpdateAt()
                )
        );
    }
}