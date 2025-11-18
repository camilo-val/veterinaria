package com.vetenrinaria.person.infrastructure.adapter.restconsumer.rest;

import com.vetenrinaria.person.domain.model.gateway.UserGateway;
import com.vetenrinaria.person.domain.model.user.User;
import com.vetenrinaria.person.infrastructure.adapter.restconsumer.dto.UserResponse;
import com.vetenrinaria.person.infrastructure.adapter.restconsumer.mapper.UserDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;
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
                .flatMap(r -> {
                    System.out.println("ENTREE: " + r);
                    return Mono.just(r);
                })
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
    public List<User> findAll() {

        return this.restClient
                .build()
                .get()
                .uri(path)
                .retrieve()
                .bodyToFlux(UserResponse.class)     // 1. Llega como flujo de UserResponse
                .map(userDtoMapper::toDomain)       // 2. Mapea 1 a 1 → User
                .doOnNext(u -> System.out.println("WEBCLIENT: " + u))  // debug opcional
                .collectList()                       // 3. Junta todo a List<User>
                .block();                            // 4. Devuelve List<User>
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
