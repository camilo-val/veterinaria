package com.vetenrinaria.person.infrastructure.adapter.mysql.adapter;

import com.vetenrinaria.person.domain.model.gateway.PersonGateway;
import com.vetenrinaria.person.domain.model.person.Person;
import com.vetenrinaria.person.infrastructure.adapter.mysql.data.PersonData;
import com.vetenrinaria.person.infrastructure.adapter.mysql.mapper.PersonMapperAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PersonAdapter implements PersonGateway {
    private final PersonData personData;
    private final PersonMapperAdapter personMapperAdapter;

    @Override
    public Optional<Person> save(Person person) {

        return Optional.ofNullable(this.personData.save(personMapperAdapter.toEntity(person)))
                .map(personMapperAdapter::toDomain);
    }

    @Override
    public Optional<Person> findById(Long id) {
        return this.personData.findById(id)
                .map(personMapperAdapter::toDomain);
    }

    @Override
    public Optional<Person> findByEmail(String email) {
        return this.personData.findByEmail(email)
                .map(personMapperAdapter::toDomain);
    }

    @Override
    public void delete(Long id) {
        this.personData.deleteById(id);
    }
}
