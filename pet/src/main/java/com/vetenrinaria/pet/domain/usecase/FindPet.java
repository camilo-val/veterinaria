package com.vetenrinaria.pet.domain.usecase;

import com.vetenrinaria.pet.domain.model.Pet;
import com.vetenrinaria.pet.domain.model.exceptions.BusinessExceptions;
import com.vetenrinaria.pet.domain.model.exceptions.BusinessMessageExceptions;
import com.vetenrinaria.pet.domain.model.gateway.PetGateway;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class FindPet {
    private final PetGateway petGateway;

    public Optional<Pet> findById(Long id) {
        return this.petGateway.findById(id).or(() -> {
            new BusinessExceptions(BusinessMessageExceptions.PET_NOT_EXIST);
            return Optional.empty();
        });
    }

    public List<Pet> findByName(String name) {
        return this.petGateway.findByName(name);
    }

    public List<Pet> findByUserId(Long userId) {
        return this.petGateway.findByUserId(userId);
    }
}
