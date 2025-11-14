package com.veterinaria.authentication.domain.usecase;

import com.veterinaria.authentication.domain.model.Token;
import com.veterinaria.authentication.domain.model.User;
import com.veterinaria.authentication.domain.model.exceptions.BusinessExceptions;
import com.veterinaria.authentication.domain.model.exceptions.BusinessMessageExceptions;
import com.veterinaria.authentication.domain.model.gateway.JwtGateway;
import com.veterinaria.authentication.domain.model.gateway.PasswordEncoderGateway;
import com.veterinaria.authentication.domain.model.gateway.UserGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class AuthenticationUseCase {
    private final UserGateway userGateway;
    private final JwtGateway jwtGateway;
    private final PasswordEncoderGateway passwordEncoderGateway;


    public Mono<Token> authenticate(User loginRequest) {
        return userGateway.login(loginRequest)
                .flatMap(userDb -> {
                    if (!passwordEncoderGateway.matches(loginRequest.getPassword(), userDb.getPassword())) {
                        return Mono.error(new BusinessExceptions(BusinessMessageExceptions.AUTHENTICATION_FAILED));
                    }

                    if (Boolean.FALSE.equals(userDb.getStatus())) {
                        return Mono.error(new BusinessExceptions(BusinessMessageExceptions.AUTHENTICATION_FAILED));
                    }

                    Token created = jwtGateway.generateJwt(userDb.getUsername(), userDb.getRole());
                    return Mono.just(Token.createToken(created.getToken(), created.getExpirationAt()));
                }).switchIfEmpty((Mono.error(() -> new RuntimeException("The user not exist"))));

    }
}
