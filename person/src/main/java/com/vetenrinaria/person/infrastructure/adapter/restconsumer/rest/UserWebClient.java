package com.vetenrinaria.person.infrastructure.adapter.restconsumer.rest;

import com.vetenrinaria.person.domain.model.gateway.UserGateway;
import com.vetenrinaria.person.domain.model.user.User;
import com.vetenrinaria.person.infrastructure.adapter.restconsumer.dto.UserResponse;
import com.vetenrinaria.person.infrastructure.adapter.restconsumer.mapper.UserDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserWebClient implements UserGateway {
    private final WebClient.Builder restClient;
    private final UserDtoMapper userDtoMapper;
    private final String path ="/api/user-service";

    @Override
    public Optional<User> save(User user) {
        return this.restClient
                .build()
                .post()
                .uri(path)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(userDtoMapper.toRequest(user))
                .retrieve()
                .bodyToMono(UserResponse.class)
                //.map(userDtoMapper::toDomain)
                .map(r -> {
                    System.out.println("WEBCLIENT: " + r.toString());
                    return userDtoMapper.toDomain(r);
                })
                .blockOptional();
    }

    @Override
    public Optional<User> update(User user) {
            return this.restClient
                    .build()
                    .put()
                        .uri(path+"/{id}", user.getId() )
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(userDtoMapper.toRequest(user))
                        .retrieve()
                        .bodyToMono(UserResponse.class)
                        .map(userDtoMapper::toDomain)
                        .map(r -> {
                            System.out.println(r.toString());
                            return r;
                        })
                        .blockOptional();
        }

    @Override
    public Optional<User> findById(Long id) {
        return this.restClient
                .build()
                .get()
                .uri(path+"/{id}", id)
                .retrieve()
                .bodyToMono(UserResponse.class)
                .map(userDtoMapper::toDomain)
                .blockOptional();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return this.restClient
                .build()
                .get()
                .uri(path+"/username{username}", username)
                .retrieve()
                .bodyToMono(UserResponse.class)
                .map(userDtoMapper::toDomain)
                .blockOptional();
    }

    @Override
    public void deleteById(Long id) {
        this.restClient
                .build()
                .delete()
                .uri(path+"/{id}", id)
                .retrieve()
                .bodyToMono(Void.class)
                .blockOptional();
    }
}
