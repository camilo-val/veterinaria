package com.vetenrinaria.user.domain.usecase;

import com.vetenrinaria.user.domain.model.User;
import com.vetenrinaria.user.domain.model.exceptions.BusinessExceptions;
import com.vetenrinaria.user.domain.model.exceptions.BusinessMessageExceptions;
import com.vetenrinaria.user.domain.model.gateway.UserGateway;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class FindUser {

    private final UserGateway userGateway;

    public Optional<User> findById(Long id) {
        return this.userGateway.findById(id).or(() -> {throw new BusinessExceptions(BusinessMessageExceptions.USER_NOT_EXIST);});
    }

    public Optional<User> findByUsername(String username) {
        return this.userGateway.findByUsername(username).or(() -> {throw new BusinessExceptions(BusinessMessageExceptions.USER_NOT_EXIST);});
    }
}
