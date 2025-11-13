package com.veterinaria.authentication.domain.model.gateway;

import com.veterinaria.authentication.domain.model.User;
import reactor.core.publisher.Mono;

public interface UserGateway {
    Mono<User> login(User user);
}
