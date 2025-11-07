package com.vetenrinaria.user.infrastructure.entrypoint.rest;

import com.vetenrinaria.user.domain.usecase.SaveUser;
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

@Component
@RequiredArgsConstructor
public class Handler {
    private final SaveUser saveUser;
    private final UserDtoMapper userDtoMapper;

    public ServerResponse create(ServerRequest request) throws ServletException, IOException {
        UserRequest body = request.body(UserRequest.class);
        UserResponse response = userDtoMapper.toResponse(this.saveUser.createUser(userDtoMapper.toDomain(body)).get());
        return ServerResponse.status(HttpStatus.CREATED).body(response);


    }

}
