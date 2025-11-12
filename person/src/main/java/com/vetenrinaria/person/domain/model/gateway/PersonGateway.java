package com.vetenrinaria.person.domain.model.gateway;

import com.vetenrinaria.person.domain.model.person.Person;

import java.util.List;
import java.util.Optional;

public interface PersonGateway {
    Optional<Person> save(Person person);
    Optional<Person> findById(Long id);
    Optional<Person> findByEmail(String email);
    List<Person> findAll();
    void delete(Long id);
}
