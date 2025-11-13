package com.vetenrinaria.user.domain.model.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class BusinessExceptions extends RuntimeException{

    public BusinessExceptions( Throwable cause, BusinessMessageExceptions businessMessageExceptions) {
        super(businessMessageExceptions.getMessage(), cause);
        this.businessMessageExceptions = businessMessageExceptions;
    }

    public BusinessExceptions(BusinessMessageExceptions businessMessageExceptions) {
        super(businessMessageExceptions.getMessage());
        this.businessMessageExceptions = businessMessageExceptions;
    }

    private final BusinessMessageExceptions businessMessageExceptions;
}
