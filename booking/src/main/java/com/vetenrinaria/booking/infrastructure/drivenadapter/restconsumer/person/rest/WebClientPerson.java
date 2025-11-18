package com.vetenrinaria.booking.infrastructure.drivenadapter.restconsumer.person.rest;

import com.vetenrinaria.booking.domain.model.person.Person;
import com.vetenrinaria.booking.domain.model.person.PersonWithUser;
import com.vetenrinaria.booking.domain.model.person.gateway.PersonGateway;
import com.vetenrinaria.booking.infrastructure.drivenadapter.restconsumer.person.dto.PersonResponse;
import com.vetenrinaria.booking.infrastructure.drivenadapter.restconsumer.person.mapper.PersonMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class WebClientPerson implements PersonGateway {
    @Value("${external-api.person.service-url}")
    private String url;
    private final WebClient.Builder webClient;
    private final PersonMapper  personMapper;

    @Override
    public List<PersonWithUser> findAll() {
        return this.webClient.baseUrl(url)
                .build()
                .get()
                .uri("/api/person-service")
                .retrieve()
                .bodyToFlux(PersonResponse.class)
                .map(personMapper::toDomain)
                .collectList()
                .block();    }

    @Override
    public Optional<PersonWithUser> findByID(Long id) {
        return this.webClient.baseUrl(url)
                .build()
                .get()
                .uri("/api/person-service/{id}", id)
                .retrieve()
                .bodyToMono(PersonResponse.class)
                .map(personMapper::toDomain)
                .blockOptional();
    }
}
