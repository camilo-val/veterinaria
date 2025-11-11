package com.vetenrinaria.person.domain.model.user;

import lombok.Getter;
import lombok.ToString;

import java.time.OffsetDateTime;
import java.util.Date;

@Getter
@ToString
public class User {
    private final Long id;
    private final String username;
    private final String password;
    private final String role;
    private final Boolean status;
    private final OffsetDateTime createdAt;
    private final OffsetDateTime updateAt;

    public User(Long id, String username, String password, String role, Boolean status, OffsetDateTime createdAt, OffsetDateTime updateAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.status = status;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
    }

}
