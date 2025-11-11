package com.vetenrinaria.user.domain.model.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BusinessMessageExceptions {
    USER_NOT_EXIST("the user not exist", "MSC-0001"),
    USER_EXIST("the user exist", "MSC-0002"),
    ROLE_INVALID("the role not is valid", "MSC-0004"),
    USER_INVALID("the user not is valid", "MSC-0003");

    private final String message;
    private final String code;
}
