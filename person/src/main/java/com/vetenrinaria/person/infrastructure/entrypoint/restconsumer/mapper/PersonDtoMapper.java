package com.vetenrinaria.person.infrastructure.entrypoint.restconsumer.mapper;

import com.vetenrinaria.person.domain.model.person.Person;
import com.vetenrinaria.person.infrastructure.entrypoint.restconsumer.dto.PersonRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ObjectFactory;

@Mapper(componentModel = "spring")
public interface PersonDtoMapper {

    // Mapea campos simples, pero ignora la construcción directa
    @Mapping(target = "userId", ignore = true)
    Person toDomain(PersonRequest request);

    // Fábrica personalizada para usar el método de dominio
    @ObjectFactory
    default Person createPerson(PersonRequest request) {
        return Person.newPerson(
                request.getId(),
                request.getName(),
                request.getEmail(),
                request.getPhone(),
                request.getAddress(),
                request.getUser() != null ? request.getUser().getId() : null
        );
    }
}
