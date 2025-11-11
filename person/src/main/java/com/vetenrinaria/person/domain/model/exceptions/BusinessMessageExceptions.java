package com.vetenrinaria.person.domain.model.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BusinessMessageExceptions {
    PERSON_NOT_EXIST("the person not exist", "MSC-0001"),
    PERSON_EXIST("the person exist", "MSC-0002"),
    PERSON_INVALID("the person not is valid", "MSC-0003");
    private final String message;
    private final String code;
}
