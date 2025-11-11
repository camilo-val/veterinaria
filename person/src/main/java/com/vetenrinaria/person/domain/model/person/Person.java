package com.vetenrinaria.person.domain.model.person;

import com.vetenrinaria.person.domain.model.exceptions.BusinessExceptions;
import com.vetenrinaria.person.domain.model.exceptions.BusinessMessageExceptions;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Person {
    private final Long id;
    private final String name;
    private final String email;
    private final String phone;
    private final String address;
    private final Long userId;

    private Person(Long id, String name, String email, String phone, String address, Long userId) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.userId = userId;
    }

    public static Person newPerson(Long id, String name, String email, String phone, String address, Long userId){
        if(name == null || email == null || phone == null || address == null){
            throw new BusinessExceptions(BusinessMessageExceptions.PERSON_INVALID);
        }
        return new Person(id, name, email, phone, address, userId);
    }

}
