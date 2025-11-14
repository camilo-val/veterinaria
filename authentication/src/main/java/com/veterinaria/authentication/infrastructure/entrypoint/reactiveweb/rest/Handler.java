package com.veterinaria.authentication.infrastructure.entrypoint.reactiveweb.rest;

import com.veterinaria.authentication.domain.usecase.AuthenticationUseCase;
import com.veterinaria.authentication.infrastructure.entrypoint.reactiveweb.dto.AuthenticationRequest;
import com.veterinaria.authentication.infrastructure.entrypoint.reactiveweb.exceptions.TechnicalExceptions;
import com.veterinaria.authentication.infrastructure.entrypoint.reactiveweb.exceptions.TechnicalMessageExceptions;
import com.veterinaria.authentication.infrastructure.entrypoint.reactiveweb.mapper.AuthenticationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class  Handler {
    private final AuthenticationUseCase authenticationUseCase;
    private final AuthenticationMapper authenticationMapper;
    public Mono<ServerResponse> login(ServerRequest request){
        return request.bodyToMono(AuthenticationRequest.class)
                .map(authenticationMapper::toDomain)
                .flatMap(authenticationUseCase::authenticate)
                .map(authenticationMapper::toResponse)
                .flatMap(ServerResponse.ok()::bodyValue)
                .switchIfEmpty(Mono.error( new TechnicalExceptions(TechnicalMessageExceptions.BAD_REQUEST)));
    }
}
