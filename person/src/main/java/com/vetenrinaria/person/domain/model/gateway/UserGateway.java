package com.vetenrinaria.person.domain.model.gateway;

import com.vetenrinaria.person.domain.model.user.User;

import java.util.Optional;

public interface UserGateway {
    Optional<User> save(User user);
    Optional<User> update(User user);
    Optional<User> findById(Long id);
    Optional<User> findByUsername(String username);
    void deleteById(Long id);
}
