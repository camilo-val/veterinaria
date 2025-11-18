package com.vetenrinaria.pet.infrastructure.drivenadapter.restconsumer.rest;

import com.vetenrinaria.pet.domain.model.person.PersonWithUser;
import com.vetenrinaria.pet.domain.model.person.gateway.PersonGateway;
import com.vetenrinaria.pet.infrastructure.drivenadapter.restconsumer.dto.PersonResponse;
import com.vetenrinaria.pet.infrastructure.drivenadapter.restconsumer.mapper.PersonMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PersonAdapter implements PersonGateway {

    private final WebClient.Builder webClientBuilder;

    private final PersonMapper personMapper;

    @Override
    public Optional<PersonWithUser> findByID(Long clientId) {
        System.out.println("entre adapter"+ new Date());
        return this.webClientBuilder.build()
                .get()
                .uri("/api/person-service/{id}",clientId)
                .retrieve()
                .bodyToMono(PersonResponse.class)
                .map(personMapper::toDomainPerson)
                .blockOptional();
    }

    @Override
    public List<PersonWithUser> findAll() {
        System.out.println("entre adapter"+ new Date());
        return this.webClientBuilder.build()
                .get()
                .uri("/api/person-service")
                .retrieve()
                .bodyToFlux(PersonResponse.class)
                .map(personMapper::toDomainPerson)
                .collectList()
                .block();    }
}
