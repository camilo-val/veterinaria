package com.vetenrinaria.pet.domain.model;

import com.vetenrinaria.pet.domain.model.exceptions.BusinessExceptions;
import com.vetenrinaria.pet.domain.model.exceptions.BusinessMessageExceptions;
import lombok.Getter;
import org.springframework.cglib.beans.BulkBeanException;

@Getter
public final class Pet {
    private final Long id;
    private final String name;
    private final Integer age;
    private final String specie;
    private final String race;
    private final Long personId;

    private Pet(Long id, String name, Integer age, String specie, String race, Long personId) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.specie = specie;
        this.race = race;
        this.personId = personId;
    }

    public  static Pet createPet(Long id, String name, Integer age, String specie, String race, Long personId) {
        if (name == null || name.isEmpty() || specie == null || specie.isEmpty() || race == null || race.isEmpty() || personId == null) {
            throw new BusinessExceptions(BusinessMessageExceptions.PET_INVALID);
        }
        return new Pet(id, name, age, specie, race, personId);
    }
}
