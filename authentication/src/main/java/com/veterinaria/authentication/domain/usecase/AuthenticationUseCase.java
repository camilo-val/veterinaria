package com.veterinaria.authentication.domain.usecase;

import com.veterinaria.authentication.domain.model.Token;
import com.veterinaria.authentication.domain.model.User;
import com.veterinaria.authentication.domain.model.exceptions.BusinessExceptions;
import com.veterinaria.authentication.domain.model.exceptions.BusinessMessageExceptions;
import com.veterinaria.authentication.domain.model.gateway.JwtGateway;
import com.veterinaria.authentication.domain.model.gateway.UserGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class AuthenticationUseCase {
    private final UserGateway userGateway;
    private final JwtGateway jwtGateway;

    public Mono<Token> authentication(User user){
        return this.userGateway.login(user).map( userBd -> {
            if (!(user.getPassword().equals(userBd.getPassword()))
                    || !(user.getUsername().equals(userBd.getUsername()))
            || !user.getStatus()){
                throw new BusinessExceptions(BusinessMessageExceptions.AUTHENTICATION_FAILED);
            }
            return userBd;
        }).map(useExist -> {
            Token newToken = this.jwtGateway.generateJwt(useExist.getUsername(),useExist.getRole());
            return Token.createToken(newToken.getToken(),newToken.getExpirationAt());
        })
                .onErrorMap(ex -> new BusinessExceptions(BusinessMessageExceptions.AUTHENTICATION_FAILED));
    }
}
