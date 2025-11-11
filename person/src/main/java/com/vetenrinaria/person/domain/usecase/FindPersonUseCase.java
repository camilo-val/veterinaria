package com.vetenrinaria.person.domain.usecase;

import com.vetenrinaria.person.domain.model.exceptions.BusinessExceptions;
import com.vetenrinaria.person.domain.model.exceptions.BusinessMessageExceptions;
import com.vetenrinaria.person.domain.model.gateway.PersonGateway;
import com.vetenrinaria.person.domain.model.gateway.UserGateway;
import com.vetenrinaria.person.domain.model.person.PersonWithUser;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class FindPersonUseCase {
    private final PersonGateway personGateway;
    private final UserGateway  userGateway;

    public Optional<PersonWithUser> findByEmail(String email) {
        return this.personGateway.findByEmail(email)
                .flatMap(person -> userGateway.findById(person.getUserId())
                            .map(user -> new PersonWithUser(person, user)))
                .or(() -> {
                    throw new  BusinessExceptions(BusinessMessageExceptions.PERSON_NOT_EXIST);
                });
    }

    public Optional<PersonWithUser> findById(Long id) {
        return this.personGateway.findById(id)
                .flatMap(person -> userGateway.findById(person.getUserId())
                        .map(user -> new PersonWithUser(person, user)))
                .or(() -> {
                    throw new  BusinessExceptions(BusinessMessageExceptions.PERSON_NOT_EXIST);
                });
    }
}
