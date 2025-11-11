package com.vetenrinaria.person.infrastructure.adapter.mysql.data;

import com.vetenrinaria.person.infrastructure.adapter.mysql.entity.PersonEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PersonData extends CrudRepository<PersonEntity, Long> {
    Optional<PersonEntity> findByEmail(String email);
    void deleteById(Long id);
}
