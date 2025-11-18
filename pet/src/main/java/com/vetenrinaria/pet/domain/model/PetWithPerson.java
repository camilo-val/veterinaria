package com.vetenrinaria.pet.domain.model;

import com.vetenrinaria.pet.domain.model.person.PersonWithUser;
import lombok.Getter;

@Getter
public class PetWithPerson {
    private final Pet pet;
    private final PersonWithUser person;

    public PetWithPerson(Pet pet, PersonWithUser person) {
        this.pet = pet;
        this.person = person;
    }
}
