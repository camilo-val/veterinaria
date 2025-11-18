package com.vetenrinaria.pet.domain.model.person;

import com.vetenrinaria.pet.domain.model.person.user.User;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PersonWithUser {
    private final Person person;
    private final User user;

    public PersonWithUser(Person person, User user) {
        this.person = person;
        this.user = user;
    }
}
