package com.veterinaria.authentication.infrastructure.entrypoint.reactiveweb.exceptions;

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
