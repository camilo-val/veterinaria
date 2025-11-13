package com.veterinaria.authentication.domain.model.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BusinessMessageExceptions {
    AUTHENTICATION_FAILED("the user or password are invalid", "MSC-0001"),
    INVALID_TOKEN("the user or password are invalid", "MSC-0001");

    private final String message;
    private final String code;
}
