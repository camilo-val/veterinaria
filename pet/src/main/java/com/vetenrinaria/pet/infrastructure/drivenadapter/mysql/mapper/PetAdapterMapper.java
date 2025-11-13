package com.vetenrinaria.pet.infrastructure.drivenadapter.mysql.mapper;

import com.vetenrinaria.pet.domain.model.Pet;
import com.vetenrinaria.pet.infrastructure.drivenadapter.mysql.entity.PetEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ObjectFactory;
import org.mapstruct.MappingTarget;

/**
 * Mapper que convierte entre la entidad JPA (PetEntity) y el modelo de dominio (Pet).
 */
@Mapper(componentModel = "spring")
public interface PetAdapterMapper {


    // De dominio a entidad (JPA)
    default PetEntity toEntity(Pet pet){
        return new PetEntity(pet.getId(),
                pet.getName(),
                pet.getAge(),
                pet.getSpecie(),
                pet.getRace(),
                pet.getPersonId());
    }

    /**
     * Fábrica para crear instancias de Pet desde una entidad, usando la lógica de dominio.
     */
    @ObjectFactory
    default Pet toDomain(PetEntity entity) {
        System.out.println("MAPER PET: " + entity.toString());
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
