package com.vetenrinaria.user.domain.usecase;

import com.vetenrinaria.user.domain.model.exceptions.BusinessExceptions;
import com.vetenrinaria.user.domain.model.exceptions.BusinessMessageExceptions;
import com.vetenrinaria.user.domain.model.gateway.UserGateway;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteUser {
    private final UserGateway userGateway;

    public void delete(String username){
        this.userGateway.findByUsername(username)
                .map(  user -> {
                    this.userGateway.deleteByUsername(user.getUsername());
                return user;})
                .or(() -> {
                    throw new BusinessExceptions(BusinessMessageExceptions.USER_NOT_EXIST);
                });
    }
}
