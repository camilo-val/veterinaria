package com.vetenrinaria.pet.domain.model.gateway;

import com.vetenrinaria.pet.domain.model.Pet;

import java.util.List;
import java.util.Optional;

public interface PetGateway {
    Optional<Pet>  findById(Long id);
    Optional<Pet> save(Pet pet);
    List<Pet> findByName(String pet);
    List<Pet> findByUserId(Long userId);
    List<Pet> findAll();
    void deleteById(Long id);
}
