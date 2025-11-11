package com.vetenrinaria.person.infrastructure.adapter.mysql.mapper;

import com.vetenrinaria.person.domain.model.person.Person;
import com.vetenrinaria.person.infrastructure.adapter.mysql.entity.PersonEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ObjectFactory;

@Mapper(componentModel = "spring")
public interface PersonMapperAdapter {

    // MapStruct detecta automáticamente campos iguales
    Person toDomain(PersonEntity entity);

    PersonEntity toEntity(Person domain);

    @ObjectFactory
    default Person createPerson(PersonEntity entity) {
        return Person.newPerson(
                entity.getId(),
                entity.getName(),
                entity.getEmail(),
                entity.getPhone(),
                entity.getAddress(),
                entity.getUserId()
        );
    }
}
