package com.vetenrinaria.user.infrastructure.entrypoint.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum TechnicalMessageExceptions {

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "TCH-0001","internal server error"),
    BAD_REQUEST(HttpStatus.INTERNAL_SERVER_ERROR, "TCH-0002","invalid request"),
    UNKNOWN(HttpStatus.INTERNAL_SERVER_ERROR, "TCH-0003","unknown error");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
