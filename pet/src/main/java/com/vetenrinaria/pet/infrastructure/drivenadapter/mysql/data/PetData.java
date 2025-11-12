package com.vetenrinaria.pet.infrastructure.drivenadapter.mysql.data;

import com.vetenrinaria.pet.infrastructure.drivenadapter.mysql.entity.PetEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Iterator;


public interface PetData extends CrudRepository<PetEntity, Long> {
    Iterator<PetEntity> findByUserId(Long userId);
    Iterator<PetEntity> findByName(String pet);
    void deleteById(Long id);
}
