package com.veterinaria.authentication.infrastructure.drivenadapter.restconsumer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TokenModel {
    private String token;
    private Instant createAt;
    private Instant expirationAt;

}
