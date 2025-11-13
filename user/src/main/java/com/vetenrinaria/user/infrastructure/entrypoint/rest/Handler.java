package com.vetenrinaria.user.infrastructure.entrypoint.rest;

import com.vetenrinaria.user.domain.model.User;
import com.vetenrinaria.user.domain.usecase.DeleteUser;
import com.vetenrinaria.user.domain.usecase.FindUser;
import com.vetenrinaria.user.domain.usecase.SaveUser;
import com.vetenrinaria.user.infrastructure.drivenadapter.mapper.UserMapper;
import com.vetenrinaria.user.infrastructure.entrypoint.dto.UserRequest;
import com.vetenrinaria.user.infrastructure.entrypoint.dto.UserResponse;
import com.vetenrinaria.user.infrastructure.entrypoint.mapper.UserDtoMapper;
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
    private final SaveUser saveUser;
    private final FindUser findUser;
    private final DeleteUser deleteUser;
    private final UserDtoMapper userDtoMapper;

    public ServerResponse findById(ServerRequest request){
        return this.findUser.findById(Long.valueOf(request.pathVariable("id")))
                .map(userDtoMapper::toResponse)
                .map(ServerResponse.ok()::body).get();
    }

    public ServerResponse findByUsername(ServerRequest request){
        return this.findUser.findByUsername(request.pathVariable("username"))
                .map(userDtoMapper::toResponse)
                .map(ServerResponse.ok()::body).get();
    }

    public ServerResponse findAll(ServerRequest request){
        List<UserResponse> responses = this.findUser.findAll().stream().map(userDtoMapper::toResponse).toList();
        return ServerResponse.ok().body(responses);
    }

    public ServerResponse create(ServerRequest request) throws ServletException, IOException {
        UserRequest body = request.body(UserRequest.class);
        User u =  this.saveUser.createUser(userDtoMapper.toDomain(body)).get();
        System.out.println("Resultado de la persistencai de datos antes del mapper: " + u);
        UserResponse response = userDtoMapper.toResponse(u);
        System.out.println("Resultado de la persistencai de datos después del mapper: " + response);
        return ServerResponse.status(HttpStatus.CREATED).body(response);

    }

    public ServerResponse update(ServerRequest request) throws ServletException, IOException {
        UserRequest body = request.body(UserRequest.class);
        Long id = Long.valueOf(request.pathVariable("id"));
        return this.saveUser.updateUser(id,
                        userDtoMapper.toDomain(body))
                .map(userDtoMapper::toResponse)
                .map(ServerResponse.ok()::body).get();
    }


    public ServerResponse delete(ServerRequest request) throws ServletException, IOException {
        this.deleteUser.delete(request.pathVariable("username"));
        return ServerResponse.noContent().build();
    }

}
