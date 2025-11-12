package com.vetenrinaria.pet.infrastructure.entrypoint.imperativerest.mapper;

import com.vetenrinaria.pet.domain.model.Pet;
import com.vetenrinaria.pet.infrastructure.entrypoint.imperativerest.dto.PetRequest;
import com.vetenrinaria.pet.infrastructure.entrypoint.imperativerest.dto.PetResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ObjectFactory;

@Mapper(componentModel = "spring")
public interface PetMapperRest {

    Pet toDomain(PetRequest petRequest);
    PetResponse toResponse(Pet pet);

    @ObjectFactory
    default Pet create(PetRequest petRequest) {
        return Pet.createPet(
                petRequest.getId(),
                petRequest.getName(),
                petRequest.getAge(),
                petRequest.getSpecie(),
                petRequest.getRace(),
                petRequest.getPersonId()
        );
    }
}
