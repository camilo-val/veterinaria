package com.vetenrinaria.person.domain.model.gateway;

import com.vetenrinaria.person.domain.model.user.User;

import java.util.List;
import java.util.Optional;

public interface UserGateway {
    Optional<User> save(User user);
    Optional<User> update(User user);
    Optional<User> findById(Long id);
    Optional<User> findByUsername(String username);
    List<User> findAll();
    void deleteById(Long id);
}
