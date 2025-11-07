package com.vetenrinaria.user.domain.model;

import com.vetenrinaria.user.domain.model.exceptions.BusinessExceptions;
import com.vetenrinaria.user.domain.model.exceptions.BusinessMessageExceptions;
import lombok.Getter;

import java.util.Date;
import java.util.Objects;

@Getter
public class User {
    private final Long id;
    private final String username;
    private final String password;
    private final Role role;
    private final Boolean status;
    private final Date createAt;
    private final Date updateAt;

    private User(Long id, String username, String password, Role role, Boolean status, Date createAt, Date updateAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.status = status;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }


    public static User createUser(Long id, String username, String password, Role role){
        if (username == null || username.isBlank() || password.isBlank() || password == null){
            throw new BusinessExceptions(BusinessMessageExceptions.USER_INVALID);
        }
        return new User(id, username,password,role,Boolean.TRUE, new Date(), null);
    }
}
