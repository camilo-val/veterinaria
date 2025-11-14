package com.veterinaria.authentication.infrastructure.entrypoint.reactiveweb.mapper;

import com.veterinaria.authentication.domain.model.Token;
import com.veterinaria.authentication.domain.model.User;
import com.veterinaria.authentication.infrastructure.entrypoint.reactiveweb.dto.AuthenticationRequest;
import com.veterinaria.authentication.infrastructure.entrypoint.reactiveweb.dto.AuthenticationResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ObjectFactory;

@Mapper(componentModel = "spring")
public interface AuthenticationMapper {
    @ObjectFactory
    default User toDomain(AuthenticationRequest authenticationRequest){
        return new User(authenticationRequest.getUsername(), authenticationRequest.getPassword(),null,null);
    }

    @ObjectFactory
    default AuthenticationResponse toResponse(Token token){
        return AuthenticationResponse.builder()
                .token(token.getToken())
                .expirationAt(token.getExpirationAt())
                .createdAt(token.getCreatedAt())
                .build();
    }
}
