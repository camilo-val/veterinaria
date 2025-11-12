package com.vetenrinaria.person.infrastructure.entrypoint.restconsumer.rest;

import com.vetenrinaria.person.domain.model.person.PersonWithUser;
import com.vetenrinaria.person.domain.usecase.DeletePerson;
import com.vetenrinaria.person.domain.usecase.FindPersonUseCase;
import com.vetenrinaria.person.domain.usecase.PersonUseCase;
import com.vetenrinaria.person.infrastructure.entrypoint.restconsumer.dto.PersonRequest;
import com.vetenrinaria.person.infrastructure.entrypoint.restconsumer.dto.PersonResponse;
import com.vetenrinaria.person.infrastructure.entrypoint.restconsumer.mapper.PersonDtoMapper;
import com.vetenrinaria.person.infrastructure.entrypoint.restconsumer.mapper.PersonWithUserMapper;
import com.vetenrinaria.person.infrastructure.entrypoint.restconsumer.mapper.UserEntryDtoMapper;
import jakarta.servlet.ServletException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class Handler {
    private final PersonUseCase personUseCase;
    private final FindPersonUseCase findPersonUseCase;
    private final PersonWithUserMapper personWithUserMapper;
    private final PersonDtoMapper  personDtoMapper;
    private final DeletePerson deletePerson;
    private final UserEntryDtoMapper userEntryDtoMapper;

    public ServerResponse findById(ServerRequest request) throws ServletException, IOException {
        return this.findPersonUseCase.findById(Long.valueOf(request.pathVariable("id")))
                .map(personWithUserMapper::toResponse)
                .map(ServerResponse.ok()::body).get();
    }

    public ServerResponse findByEmail(ServerRequest request) throws ServletException, IOException {
        return this.findPersonUseCase.findByEmail(request.pathVariable("email"))
                .map(personWithUserMapper::toResponse)
                .map(ServerResponse.ok()::body).get();
    }
    public ServerResponse findAll(ServerRequest request) {
        List<PersonResponse> person = this.findPersonUseCase.findAll().stream().map(personWithUserMapper::toResponse).toList();
        return ServerResponse.ok().body((person));
    }
    public ServerResponse create(ServerRequest request) throws ServletException, IOException {
        PersonRequest personRequest = request.body(PersonRequest.class);
        System.out.printf("PersonRequest: %s%n", personRequest.toString());
        System.out.println("ASAD" + personDtoMapper.toDomain(personRequest));
        return this.personUseCase.create(personDtoMapper.toDomain(personRequest), userEntryDtoMapper.toDomain(personRequest.getUser()))
                .map(r -> {
                    System.out.println("MAPAPAPAP!: " + r.toString());
                    return personWithUserMapper.toResponse(r);})
                .map(ServerResponse.ok()::body).get();
    }

    public ServerResponse update(ServerRequest request) throws ServletException, IOException {
        PersonRequest personRequest = request.body(PersonRequest.class);
        Long id = Long.valueOf(request.pathVariable("id"));
        return this.personUseCase.update(id,personDtoMapper.toDomain(personRequest), userEntryDtoMapper.toDomain(personRequest.getUser()))
                .map(personWithUserMapper::toResponse)
                .map(ServerResponse.ok()::body).get();
    }

    public ServerResponse delete(ServerRequest request){
        Long id = Long.valueOf(request.pathVariable("id"));
        this.deletePerson.deletePerson(id);
        return ServerResponse.status(HttpStatus.NO_CONTENT).build();
    }
}