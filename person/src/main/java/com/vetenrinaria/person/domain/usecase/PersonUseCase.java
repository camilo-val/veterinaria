package com.vetenrinaria.person.domain.usecase;

import com.vetenrinaria.person.domain.model.exceptions.BusinessExceptions;
import com.vetenrinaria.person.domain.model.exceptions.BusinessMessageExceptions;
import com.vetenrinaria.person.domain.model.gateway.PersonGateway;
import com.vetenrinaria.person.domain.model.gateway.UserGateway;
import com.vetenrinaria.person.domain.model.person.Person;
import com.vetenrinaria.person.domain.model.person.PersonWithUser;
import com.vetenrinaria.person.domain.model.user.User;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class PersonUseCase {
    private final PersonGateway personGateway;
    private final UserGateway userGateway;

    public Optional<PersonWithUser> create(Person person, User user) {
        if (exist(person.getEmail())){
            throw new BusinessExceptions(BusinessMessageExceptions.PERSON_EXIST);
        }
        System.out.println("USER CASE: " + user.toString());
        return this.userGateway.save(user)
                .flatMap(newUser -> {
                    System.out.println("USER CASE: " + newUser.toString());
                    Person newPerson = Person.newPerson(person.getUserId(),person.getName(), person.getEmail(), person.getPhone(), person.getAddress(), newUser.getId());
                    return this.personGateway.save(newPerson)
                            .map(p -> new PersonWithUser(p, newUser))
                            .or(() -> {
                                userGateway.deleteById(newUser.getId());
                                return Optional.empty();
                            });
                });
    }

    public Optional<PersonWithUser> update(Person person, User user) {
        return Optional.empty();
    }

    private Boolean exist(String email) {
        return this.personGateway.findByEmail(email).isPresent();
    }
}
