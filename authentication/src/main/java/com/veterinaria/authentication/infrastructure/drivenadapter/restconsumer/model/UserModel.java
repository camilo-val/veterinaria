package com.veterinaria.authentication.infrastructure.drivenadapter.restconsumer.model;

import com.veterinaria.authentication.domain.model.Role;
import com.veterinaria.authentication.domain.model.exceptions.BusinessExceptions;
import com.veterinaria.authentication.domain.model.exceptions.BusinessMessageExceptions;
import lombok.Getter;
import lombok.ToString;

import java.time.OffsetDateTime;

@Getter
@ToString
public class UserModel {
    private final Long id;
    private final String username;
    private final String password;
    private final Role role;
    private final Boolean status;
    private final OffsetDateTime createdAt;
    private final OffsetDateTime updateAt;

    public UserModel(Long id, String username, String password, Role role, Boolean status, OffsetDateTime createdAt, OffsetDateTime updateAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.status = status;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
    }
}
