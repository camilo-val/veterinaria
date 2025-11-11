package com.vetenrinaria.person.domain.model.gateway;

import com.vetenrinaria.person.domain.model.person.Person;

import java.util.Optional;

public interface PersonGateway {
    Optional<Person> save(Person person);
    Optional<Person> findById(Long id);
    Optional<Person> findByEmail(String email);
    void delete(Long id);
}
