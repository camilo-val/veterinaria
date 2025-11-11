package com.vetenrinaria.user.domain.model.gateway;

import com.vetenrinaria.user.domain.model.User;

import java.util.Optional;

public interface UserGateway {
    Optional<User> save(User user);
    Optional<User> findById(Long id);
    Optional<User> findByUsername(String username);
    void deleteByUsername(String username);
}
