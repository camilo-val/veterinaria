package com.veterinaria.authentication.infrastructure.entrypoint.reactiveweb.dto;

import com.veterinaria.authentication.domain.model.Role;
import lombok.*;

import java.time.OffsetDateTime;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Getter
@ToString
public class AuthenticationRequest {
    private String username;
    private String password;
}
