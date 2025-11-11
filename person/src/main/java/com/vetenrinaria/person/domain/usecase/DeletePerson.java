package com.vetenrinaria.person.domain.usecase;

import com.vetenrinaria.person.domain.model.gateway.PersonGateway;
import com.vetenrinaria.person.domain.model.gateway.UserGateway;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeletePerson {
    private final PersonGateway personGateway;
    private final UserGateway userGateway;

    public void deletePerson(Long id){
        this.personGateway.findById(id)
                .map(person -> {
                    this.userGateway.deleteById(id);
                    return person;
                })
                .map(user -> {
                    this.userGateway.deleteById(id);
                    return user;
                });
    }
}
