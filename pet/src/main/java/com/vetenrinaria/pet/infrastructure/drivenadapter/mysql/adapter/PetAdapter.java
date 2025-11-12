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

@Component
@RequiredArgsConstructor
public class PetAdapter implements PetGateway {
    private final PetAdapterMapper  petAdapterMapper;
    private final PetData petData;

    @Override
    public Optional<Pet> findById(Long id) {
        return this.petData.findById(id)
                .map(petAdapterMapper::toDomain);
    }

    @Override
    public Optional<Pet> save(Pet pet) {
        return Optional.ofNullable(this.petData.save(petAdapterMapper.toEntity(pet)))
                .map(petAdapterMapper::toDomain);
    }

    @Override
    public List<Pet> findByName(String pet) {
        //noinspection unchecked
        return Collections.unmodifiableList((List<Pet>) this.petData.findByName(pet));
    }

    @Override
    public List<Pet> findByUserId(Long userId) {
        //noinspection unchecked
        return Collections.unmodifiableList((List<Pet>) this.petData.findByUserId(userId));
    }

    @Override
    public void deleteById(Long id) {
        this.petData.deleteById(id);
    }
}
