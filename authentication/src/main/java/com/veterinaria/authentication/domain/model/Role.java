package com.veterinaria.authentication.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.veterinaria.authentication.domain.model.exceptions.BusinessExceptions;
import com.veterinaria.authentication.domain.model.exceptions.BusinessMessageExceptions;
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
}
