package com.vetenrinaria.user.infrastructure.drivenadapter.data;

import com.vetenrinaria.user.infrastructure.drivenadapter.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
    void deleteByUsername(String username);
}
