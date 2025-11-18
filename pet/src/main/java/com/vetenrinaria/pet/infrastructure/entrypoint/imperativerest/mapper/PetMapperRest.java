package com.vetenrinaria.pet.infrastructure.entrypoint.imperativerest.mapper;

import com.vetenrinaria.pet.domain.model.Pet;
import com.vetenrinaria.pet.domain.model.PetWithPerson;
import com.vetenrinaria.pet.infrastructure.drivenadapter.restconsumer.dto.PersonResponse;
import com.vetenrinaria.pet.infrastructure.drivenadapter.restconsumer.dto.UserResponse;
import com.vetenrinaria.pet.infrastructure.drivenadapter.restconsumer.mapper.PersonMapper;
import com.vetenrinaria.pet.infrastructure.entrypoint.imperativerest.dto.PetRequest;
import com.vetenrinaria.pet.infrastructure.entrypoint.imperativerest.dto.PetResponse;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.ObjectFactory;

import java.util.Date;

@Mapper(componentModel = "spring", uses = { PersonMapper.class })
public interface PetMapperRest {
    Pet toDomain(PetRequest petRequest, @Context PersonMapper personMapper);
    default PetWithPerson toDomainWithPerson(
            PetRequest request,
            PersonResponse personResponse,
            @Context PersonMapper personMapper
    ) {
        return new PetWithPerson(
                toDomain(request, personMapper),
                personMapper.toDomainPerson(personResponse)
        );
    }

   default Pet toDomain(PetRequest petRequest){
        return Pet.createPet(
                petRequest.getId(),
                petRequest.getName(),
                petRequest.getAge(),
                petRequest.getSpecie(),
                petRequest.getRace(),
                petRequest.getPersonId()
        );
    }

    default PetResponse toResponse(PetWithPerson petWithPerson) {
        System.out.println("entre mapper entry"+ new Date());
        return PetResponse.builder()
                .id(petWithPerson.getPet().getId())
                .name(petWithPerson.getPet().getName())
                .age(petWithPerson.getPet().getAge())
                .specie(petWithPerson.getPet().getSpecie())
                .race(petWithPerson.getPet().getRace())
                .person(
                        // devolvemos PersonResponse listo para la vista
                        new PersonResponse(
                                petWithPerson.getPerson().getPerson().getId(),
                                petWithPerson.getPerson().getPerson().getName(),
                                petWithPerson.getPerson().getPerson().getEmail(),
                                petWithPerson.getPerson().getPerson().getPhone(),
                                petWithPerson.getPerson().getPerson().getAddress(),
                                new UserResponse(petWithPerson.getPerson().getUser().getId(),
                                        petWithPerson.getPerson().getUser().getUsername(),
                                        petWithPerson.getPerson().getUser().getPassword(),
                                        petWithPerson.getPerson().getUser().getRole(),
                                        petWithPerson.getPerson().getUser().getStatus(),
                                        petWithPerson.getPerson().getUser().getCreatedAt(),
                                        petWithPerson.getPerson().getUser().getUpdateAt())
                        )
                )
                .build();
    }

    @ObjectFactory
    default Pet create(PetRequest petRequest, @Context PersonMapper personMapper) {
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