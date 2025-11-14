package com.vetenrinaria.booking.domain.model.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BusinessMessageExceptions {
    INVALID_BOOKING("invalid booking ", "MSB-0001"),
    INVALID_CLIENT("invalid client ", "MSB-0002"),
    INVALID_EMPLOYEE("invalid employee ", "MSB-0003");

    private final String message;
    private final String code;
}
