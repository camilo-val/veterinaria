package com.veterinaria.authentication.domain.model.gateway;

public interface PasswordEncoderGateway {

    boolean matches(String rawPassword, String hashedPassword);
}
