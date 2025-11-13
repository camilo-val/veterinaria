package com.vetenrinaria.pet.domain.usecase;

import com.vetenrinaria.pet.domain.model.Pet;
import com.vetenrinaria.pet.domain.model.exceptions.BusinessExceptions;
import com.vetenrinaria.pet.domain.model.exceptions.BusinessMessageExceptions;
import com.vetenrinaria.pet.domain.model.gateway.PetGateway;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class PetUseCase {

    private final PetGateway petGateway;

    public Optional<Pet> update(Long id,Pet pet) {
        List<Pet> findByName = petGateway.findByName(pet.getName());
        return petGateway.findById(id).flatMap( petBd -> {
            if (findByName.stream().anyMatch(pet1 -> !pet1.getPersonId().equals(pet.getPersonId()))) {
                throw new BusinessExceptions(BusinessMessageExceptions.PET_EXIST);
            }
            Pet updatePet = Pet.createPet(id, pet.getName(), pet.getAge(),  pet.getSpecie(), pet.getRace(), pet.getPersonId());
            return this.petGateway.save(updatePet);
        });
    }

    public Optional<Pet> save(Pet pet) {
        System.out.println("NEW PET 0: " + pet.toString());

        List<Pet> petsByName = petGateway.findByName(pet.getName());
        List<Pet> petsByPerson = petGateway.findByUserId(pet.getPersonId());

        boolean exists = petsByPerson.stream()
                .anyMatch(existingPet -> petsByName.stream()
                        .anyMatch(p -> p.getName().equals(existingPet.getName())));

        if (exists) {
            throw new BusinessExceptions(BusinessMessageExceptions.PET_EXIST);
        }

        Pet newPet = Pet.createPet(
                pet.getId(),
                pet.getName(),
                pet.getAge(),
                pet.getSpecie(),
                pet.getRace(),
                pet.getPersonId()
        );
        System.out.println("NEW PET: " + newPet.toString());

        return petGateway.save(newPet);
    }

}
