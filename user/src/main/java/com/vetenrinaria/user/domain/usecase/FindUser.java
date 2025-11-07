package com.vetenrinaria.user.domain.usecase;

import com.vetenrinaria.user.domain.model.User;
import com.vetenrinaria.user.domain.model.exceptions.BusinessExceptions;
import com.vetenrinaria.user.domain.model.exceptions.BusinessMessageExceptions;
import com.vetenrinaria.user.domain.model.gateway.UserGateway;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FindUser {

    private final UserGateway userGateway;

    public User findById(Long id) {
        return this.userGateway.findById(id).orElseThrow(() -> new BusinessExceptions(BusinessMessageExceptions.USER_NOT_EXIST));
    }

    public User findByUsername(String username) {
        return this.userGateway.findByUsername(username).orElseThrow(() -> new BusinessExceptions(BusinessMessageExceptions.USER_NOT_EXIST));
    }
}
