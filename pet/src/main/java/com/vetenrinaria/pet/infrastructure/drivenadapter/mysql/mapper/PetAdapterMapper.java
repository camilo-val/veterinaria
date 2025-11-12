package com.vetenrinaria.pet.infrastructure.drivenadapter.mysql.mapper;

import com.vetenrinaria.pet.domain.model.Pet;
import com.vetenrinaria.pet.infrastructure.drivenadapter.mysql.entity.PetEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ObjectFactory;

@Mapper(componentModel = "spring")
public interface PetAdapterMapper {
    Pet toDomain(PetEntity petEntity);
    PetEntity toEntity(Pet pet);

    @ObjectFactory
    default Pet create(PetEntity entity) {
        return Pet.createPet(
                entity.getId(),
                entity.getName(),
                entity.getAge(),
                entity.getSpecie(),
                entity.getRace(),
                entity.getPersonId()
        );
    }
}
