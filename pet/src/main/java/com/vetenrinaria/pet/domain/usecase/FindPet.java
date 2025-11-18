package com.vetenrinaria.pet.domain.usecase;

import com.vetenrinaria.pet.domain.model.PetWithPerson;
import com.vetenrinaria.pet.domain.model.exceptions.BusinessExceptions;
import com.vetenrinaria.pet.domain.model.exceptions.BusinessMessageExceptions;
import com.vetenrinaria.pet.domain.model.gateway.PetGateway;
import com.vetenrinaria.pet.domain.model.person.PersonWithUser;
import com.vetenrinaria.pet.domain.model.person.gateway.PersonGateway;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class FindPet {
    private final PetGateway petGateway;
    private final PersonGateway personGateway;

    public Optional<PetWithPerson> findById(Long id) {
        return this.petGateway.findById(id)
                .flatMap(pet -> this.personGateway.findByID(pet.getPersonId())
                        .map(person -> new PetWithPerson(pet,person)))
                .or(() -> {
            throw new BusinessExceptions(BusinessMessageExceptions.PET_NOT_EXIST);
        });
    }

    public List<PetWithPerson> findByName(String name) {
        return this.petGateway.findByName(name)
                .stream()
                .map(pet -> this.personGateway.findByID(pet.getPersonId())
                        .map(person -> new PetWithPerson(pet,person)).get()).toList();
    }

    public List<PetWithPerson> findByUserId(Long userId) {
        System.out.println("entre use"+ new Date());
        return this.petGateway.findByUserId(userId)
                .stream()
                .map(pet -> this.personGateway.findByID(pet.getPersonId())
                        .map(person -> new PetWithPerson(pet,person)).get()).toList();
    }

    public List<PetWithPerson> findAll() {
        List<PersonWithUser> persons = this.personGateway.findAll();
        return this.petGateway.findAll()
                .stream()
                .flatMap(pet -> persons.stream().filter(p -> p.getPerson().getId().equals(pet.getPersonId()))
                        .map(person -> new PetWithPerson(pet,person))).toList();
    }
}
