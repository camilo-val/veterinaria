package com.vetenrinaria.user.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.vetenrinaria.user.domain.model.exceptions.BusinessExceptions;
import com.vetenrinaria.user.domain.model.exceptions.BusinessMessageExceptions;
import lombok.Getter;

@Getter
public enum Role {
    ROOT("ADMIN"),
    CLIENT("CLIENT"),
    VETERINARIAN("VETERINARIAN");

    private final String role;

    Role(String role) {
        this.role = role;
    }

    @JsonCreator
    public static Role from(String value) {
        try {
            return Role.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new BusinessExceptions(BusinessMessageExceptions.ROLE_INVALID);
        }
    }
}
