package com.veterinaria.authentication.infrastructure.drivenadapter.restconsumer.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.veterinaria.authentication.domain.model.Role;
import lombok.*;

import java.time.OffsetDateTime;

@Builder(toBuilder = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserResponse {
    private Long id;
    private String username;
    @JsonIgnore
    private String password;
    private Role role;
    private Boolean status;
    private OffsetDateTime createdAt;
    private OffsetDateTime updateAt;
}
