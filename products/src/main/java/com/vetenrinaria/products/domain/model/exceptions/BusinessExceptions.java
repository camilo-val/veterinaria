package com.vetenrinaria.products.domain.model.exceptions;

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
