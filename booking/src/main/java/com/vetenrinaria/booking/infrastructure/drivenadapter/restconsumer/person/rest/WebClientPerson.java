package com.vetenrinaria.booking.infrastructure.drivenadapter.restconsumer.person.rest;

import com.vetenrinaria.booking.domain.model.person.Person;
import com.vetenrinaria.booking.infrastructure.drivenadapter.restconsumer.person.dto.PersonResponse;
import com.vetenrinaria.booking.infrastructure.drivenadapter.restconsumer.person.mapper.PersonMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@RequiredArgsConstructor
public class WebClientPerson {
    @Value("${external-api.person.service-url}")
    private String url;
    private final WebClient.Builder webClient;
    private final PersonMapper  personMapper;

    public Optional<Person> getPerson(Long id) {
        return this.webClient.baseUrl(url)
                .build()
                .get()
                .uri("/api/person.service/{id}", id)
                .retrieve()
                .bodyToMono(PersonResponse.class)
                .map(personMapper::toDomain)
                .blockOptional();
    }
}
