package com.vetenrinaria.user.infrastructure.drivenadapter.adapter;

import com.vetenrinaria.user.domain.model.User;
import com.vetenrinaria.user.domain.model.gateway.UserGateway;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserAdapter implements UserGateway {
    @Override
    public Optional<User> save(User user) {
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return Optional.empty();
    }

    @Override
    public void deleteByUsername(Long id) {

    }
}
