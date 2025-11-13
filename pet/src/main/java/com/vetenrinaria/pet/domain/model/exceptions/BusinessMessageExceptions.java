package com.vetenrinaria.pet.domain.model.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BusinessMessageExceptions {
    PET_NOT_EXIST("the pet not exist", "MSP-0001"),
    PET_EXIST("the pet exist", "MSP-0002"),
    PET_INVALID("the pet not is valid", "MSP-0003");
    private final String message;
    private final String code;
}
