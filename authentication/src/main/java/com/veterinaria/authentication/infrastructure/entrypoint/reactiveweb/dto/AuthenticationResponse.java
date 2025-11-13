package com.veterinaria.authentication.infrastructure.entrypoint.reactiveweb.dto;

import lombok.*;

import java.time.Instant;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Getter
@ToString
public class AuthenticationResponse {
    private String token;
    private Instant expirationAt;
    private Instant createdAt;
}
