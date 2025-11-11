package com.vetenrinaria.user.domain.model;

import com.vetenrinaria.user.domain.model.exceptions.BusinessExceptions;
import com.vetenrinaria.user.domain.model.exceptions.BusinessMessageExceptions;
import lombok.Getter;
import lombok.ToString;

import java.time.OffsetDateTime;

@Getter
@ToString
public class User {
    private final Long id;
    private final String username;
    private final String password;
    private final Role role;
    private final Boolean status;
    private final OffsetDateTime createdAt;
    private final OffsetDateTime updateAt;

    private User(Long id, String username, String password, Role role, Boolean status, OffsetDateTime createdAt, OffsetDateTime updateAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.status = status;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
    }


    public static User createUser(Long id, String username, String password, Role role, Boolean status, OffsetDateTime createdAt, OffsetDateTime updateAt){
        if (username == null || username.isBlank() || password.isBlank() || password == null){
            throw new BusinessExceptions(BusinessMessageExceptions.USER_INVALID);
        }
        return new User(id, username,password,role, status, createdAt, updateAt);
    }
}
