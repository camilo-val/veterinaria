package com.veterinaria.authentication.infrastructure.drivenadapter.restconsumer.adapter;

import com.veterinaria.authentication.domain.model.User;
import com.veterinaria.authentication.domain.model.gateway.UserGateway;
import com.veterinaria.authentication.infrastructure.drivenadapter.restconsumer.dto.UserResponse;
import com.veterinaria.authentication.infrastructure.drivenadapter.restconsumer.mapper.UserMapperAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class AuthenticationAdapter implements UserGateway {
    private final WebClient.Builder builder;
    private final UserMapperAdapter userMapperAdapter;

    @Override
    public Mono<User> login(User user) {
        return this.builder.build()
                .get()
                .uri("/{username}", user.getUsername())
                .retrieve()
                .bodyToMono(UserResponse.class)
                .map(userMapperAdapter::toDomain);
    }

}
