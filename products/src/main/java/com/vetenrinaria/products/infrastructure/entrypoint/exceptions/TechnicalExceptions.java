package com.vetenrinaria.products.infrastructure.entrypoint.exceptions;

import lombok.Getter;

@Getter
public class TechnicalExceptions extends RuntimeException{
    private final TechnicalMessageExceptions technicalMessageExceptions;

    public TechnicalExceptions(TechnicalMessageExceptions technicalMessageExceptions) {
        super(technicalMessageExceptions.getMessage());
        this.technicalMessageExceptions = technicalMessageExceptions;
    }

    public TechnicalExceptions(Throwable cause, TechnicalMessageExceptions technicalMessageExceptions) {
        super(technicalMessageExceptions.getMessage(), cause);
        this.technicalMessageExceptions = technicalMessageExceptions;
    }
}
