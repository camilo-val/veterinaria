package com.veterinaria.authentication.domain.model;

import com.veterinaria.authentication.domain.model.exceptions.BusinessExceptions;
import com.veterinaria.authentication.domain.model.exceptions.BusinessMessageExceptions;
import lombok.Getter;

import java.time.Instant;

@Getter
public class Token {
    private final String token;
    private final Instant createdAt;
    private final Instant expirationAt;

    private Token(String token, Instant createdAt, Instant expirationAt) {
        this.token = token;
        this.createdAt = createdAt;
        this.expirationAt = expirationAt;
    }

    public static Token createToken(String token, Instant expirationAt){
        if (token == null || token.isEmpty()){
            throw new BusinessExceptions(BusinessMessageExceptions.INVALID_TOKEN);
        }
        return new Token(token,Instant.now(),expirationAt);

    }
}
