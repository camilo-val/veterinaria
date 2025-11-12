package com.vetenrinaria.pet.domain.usecase;

import com.vetenrinaria.pet.domain.model.Pet;
import com.vetenrinaria.pet.domain.model.exceptions.BusinessExceptions;
import com.vetenrinaria.pet.domain.model.exceptions.BusinessMessageExceptions;
import com.vetenrinaria.pet.domain.model.gateway.PetGateway;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeletePet {
    private final PetGateway petGateway;

    public void deleteById(Long id) {
        this.petGateway.findById(id)
                .map(pet -> {
                    this.petGateway.deleteById(pet.getId());
                    return pet;
                }).or(() -> {throw new BusinessExceptions(BusinessMessageExceptions.PET_NOT_EXIST);});
    }

}
