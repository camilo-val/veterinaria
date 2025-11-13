package com.vetenrinaria.pet.infrastructure.drivenadapter.mysql.adapter;

import com.vetenrinaria.pet.domain.model.Pet;
import com.vetenrinaria.pet.domain.model.gateway.PetGateway;
import com.vetenrinaria.pet.infrastructure.drivenadapter.mysql.data.PetData;
import com.vetenrinaria.pet.infrastructure.drivenadapter.mysql.mapper.PetAdapterMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Component
@RequiredArgsConstructor
public class PetAdapter implements PetGateway {
    private final PetAdapterMapper  petAdapterMapper;
    private final PetData petData;

    @Override
    public Optional<Pet> findById(Long id) {
        return this.petData.findById(id)
                .map(r -> {
                    System.out.println("RESPONSE BD: " + r.toString());
                    return r;
                })
                .map(petAdapterMapper::toDomain);
    }

    @Override
    public Optional<Pet> save(Pet pet) {
        System.out.println("NEW PET aDAPTER: " + pet.toString());
        return Optional.ofNullable(this.petData.save(petAdapterMapper.toEntity(pet)))
                .map(petAdapterMapper::toDomain);
    }

    @Override
    public List<Pet> findByName(String pet) {
        return StreamSupport.stream(this.petData.findByName(pet).spliterator(),false)
                .map(petAdapterMapper::toDomain)
                .toList();
    }

    @Override
    public List<Pet> findByUserId(Long userId) {
        return StreamSupport.stream(this.petData.findByPersonId(userId).spliterator(), false)
                .map(petAdapterMapper::toDomain)
                .toList();
    }

    @Override
    public List<Pet> findAll() {
        return StreamSupport.stream(this.petData.findAll().spliterator(), false)
                .map(petAdapterMapper::toDomain)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        this.petData.deleteById(id);
    }
}
