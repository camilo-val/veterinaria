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
        if (exist(person.getEmail())) {
            throw new BusinessExceptions(BusinessMessageExceptions.PERSON_EXIST);
        }
        System.out.println("USER CASE: " + user.toString());
        User createUser = new User(user.getId() ,person.getEmail(), user.getPassword(), user.getRole(), user.getStatus(),user.getCreatedAt(), user.getUpdateAt());
        return this.userGateway.save(createUser)
                .flatMap(newUser -> {
                    System.out.println("USER CASE: " + newUser.toString());
                    Person newPerson = Person.newPerson(person.getUserId(),person.getName(), person.getEmail(),
                            person.getPhone(), person.getAddress(), newUser.getId());
                    return this.personGateway.save(newPerson)
                            .map(p -> new PersonWithUser(p, newUser))
                            .or(() -> {
                                userGateway.deleteById(newUser.getId());
                                return Optional.empty();
                            });
                });
    }

    public Optional<PersonWithUser> update(Long id,Person person, User user) {
        Optional<Person> personByEmail = this.personGateway.findByEmail(person.getEmail());
        Optional<Person> personById = this.personGateway.findById(id);
        if (personById.isPresent()){
            if (personByEmail.isPresent() && !personByEmail.get().getEmail().equals(personById.get().getEmail())){
                throw new BusinessExceptions(BusinessMessageExceptions.PERSON_EXIST);
            }
        }

        Person updatePerson = Person.newPerson(id, person.getName(),person.getEmail(),person.getPhone(),person.getAddress(),person.getUserId());

        return this.personGateway.save(updatePerson)
                .map(responsse -> new PersonWithUser(responsse, this.userGateway.update(user).get()));
    }

    private Boolean exist(String email) {
        return this.personGateway.findByEmail(email).isPresent();
    }
}
