package com.veterinaria.authentication.infrastructure.entrypoint.reactiveweb.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum TechnicalMessageExceptions {

    BAD_REQUEST(HttpStatus.INTERNAL_SERVER_ERROR, "TCH-0002","invalid request");
    private final HttpStatus status;
    private final String code;
    private final String message;
}
