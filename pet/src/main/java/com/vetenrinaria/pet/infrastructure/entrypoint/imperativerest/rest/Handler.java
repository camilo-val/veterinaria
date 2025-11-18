package com.vetenrinaria.pet.infrastructure.entrypoint.imperativerest.rest;

import com.vetenrinaria.pet.domain.model.Pet;
import com.vetenrinaria.pet.domain.usecase.DeletePet;
import com.vetenrinaria.pet.domain.usecase.FindPet;
import com.vetenrinaria.pet.domain.usecase.PetUseCase;
import com.vetenrinaria.pet.infrastructure.entrypoint.imperativerest.dto.PetRequest;
import com.vetenrinaria.pet.infrastructure.entrypoint.imperativerest.dto.PetResponse;
import com.vetenrinaria.pet.infrastructure.entrypoint.imperativerest.mapper.PetMapperRest;
import jakarta.servlet.ServletException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class Handler {
    private final PetUseCase petUseCase;
    private final FindPet findPet;
    private final DeletePet deletePet;
    private final PetMapperRest petMapperRest;

    public ServerResponse save(ServerRequest request) throws ServletException, IOException {
        PetRequest body = request.body(PetRequest.class);
        return this.petUseCase.save(petMapperRest.toDomain(body))
                .map(petMapperRest::toResponse)
                .map(ServerResponse.created(request.uri())::body)
                .get();
    }

    public ServerResponse update(ServerRequest request) throws ServletException, IOException {
        Long id = Long.valueOf(request.pathVariable("id"));
        PetRequest body = request.body(PetRequest.class);
        return this.petUseCase.update(id, petMapperRest.toDomain(body))
                .map(petMapperRest::toResponse)
                .map(r -> {
                    System.out.println("entre handler"+ new Date());
                    return r;
                })
                .map(ServerResponse.created(request.uri())::body)
                .get();
    }

    public ServerResponse findById(ServerRequest request) {
        return this.findPet.findById(Long.valueOf(request.pathVariable("id")))
                .map(petMapperRest::toResponse)
                .map(ServerResponse.ok()::body).get();
    }

    ServerResponse findByPersonId(ServerRequest request) {
        List< PetResponse> response =this.findPet.findByUserId(Long.valueOf(request.pathVariable("personid")))
                .stream().map(petMapperRest::toResponse).toList();
       return ServerResponse.ok().body(response);
    }

    ServerResponse findall(ServerRequest request) {
        List< PetResponse> response =this.findPet.findAll()
                .stream().map(petMapperRest::toResponse).toList();
        return ServerResponse.ok().body(response);
    }

    ServerResponse findByName(ServerRequest request) {
        List< PetResponse> response =this.findPet.findByName(request.pathVariable("name"))
                .stream().map(petMapperRest::toResponse).toList();
        return ServerResponse.ok().body(response);
    }

    public ServerResponse delete(ServerRequest request) {
        this.deletePet.deleteById(Long.valueOf(request.pathVariable("id")));
        return ServerResponse.noContent().build();
    }
}
