package com.vetenrinaria.pet.domain.model.person;

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

    public Person(Long id, String name, String email, String phone, String address, Long userId) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.userId = userId;
    }


}
