package com.veterinaria.authentication.domain.model.gateway;

import com.veterinaria.authentication.domain.model.Role;
import com.veterinaria.authentication.domain.model.Token;

public interface JwtGateway {
    Token generateJwt(String username, Role role);
    Token refreshToken(String username, Role role);

}
