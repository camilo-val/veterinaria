package com.vetenrinaria.products.infrastructure.entrypoint.rest;


import com.vetenrinaria.products.domain.model.exceptions.BusinessExceptions;
import com.vetenrinaria.products.infrastructure.entrypoint.exceptions.TechnicalExceptions;
import com.vetenrinaria.products.infrastructure.entrypoint.exceptions.TechnicalMessageExceptions;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.HandlerFilterFunction;
import org.springframework.web.servlet.function.HandlerFunction;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import java.util.Date;
import java.util.Map;

@Component
public class WebHandlerException implements HandlerFilterFunction<ServerResponse, ServerResponse> {

    @Override
    public ServerResponse filter(ServerRequest request, HandlerFunction<ServerResponse> next) throws Exception {

        try {
            return next.handle(request);
        } catch (Exception ex) {
            HttpStatus status = resolveHttpStatus(ex);
            Map<String, Object> error = buildMap(ex);
            return ServerResponse.status(status)
                    .body(error);
        }
    }

    private HttpStatus resolveHttpStatus(Throwable ex){
        if (ex instanceof BusinessExceptions businessExceptions){
            return switch (businessExceptions.getBusinessMessageExceptions()){
                case PRODUCT_EXIST , PRODUCT_INVALID -> HttpStatus.CONFLICT;
                case PRODUCT_NOT_EXIST -> HttpStatus.NOT_FOUND;
            };
        }else if (ex instanceof TechnicalExceptions technicalExceptions){
            return switch (technicalExceptions.getTechnicalMessageExceptions()){
                case BAD_REQUEST ->  HttpStatus.BAD_REQUEST;
                default -> HttpStatus.INTERNAL_SERVER_ERROR;
            };
        }
        return TechnicalMessageExceptions.UNKNOWN.getStatus();
    }

    private Map<String, Object> buildMap(Throwable ex){
        if (ex instanceof BusinessExceptions businessExceptions){
            return Map.of(
                    "code", businessExceptions.getBusinessMessageExceptions().getCode(),
                    "type", "BUSINESS",
                    "message", businessExceptions.getBusinessMessageExceptions().getMessage(),
                    "date", new Date().toString()
            );
        }else if (ex instanceof TechnicalExceptions technicalExceptions){
            return Map.of(
                    "code", technicalExceptions.getTechnicalMessageExceptions().getCode(),
                    "type", "BUSINESS",
                    "message", technicalExceptions.getTechnicalMessageExceptions().getMessage(),
                    "date", new Date().toString()
            );
        }
        return Map.of(
                "code", TechnicalMessageExceptions.UNKNOWN.getCode(),
                "type", "UNKNOWN",
                "message", TechnicalMessageExceptions.UNKNOWN.getMessage(),
                "date", new Date().toString()
        );
    }
}
