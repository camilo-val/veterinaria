package com.vetenrinaria.user.infrastructure.drivenadapter.adapter;

import com.vetenrinaria.user.domain.model.User;
import com.vetenrinaria.user.domain.model.gateway.UserGateway;
import com.vetenrinaria.user.infrastructure.drivenadapter.data.UserRepository;
import com.vetenrinaria.user.infrastructure.drivenadapter.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserAdapter implements UserGateway {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public Optional<User> save(User user) {

        return Optional.ofNullable(this.userRepository.save(userMapper.toEntity(user)))
                .map(u -> {
                    System.out.println("NEW USER ADAPTER: " + u);
                    return u;

                })
                .map(userMapper::toDomain)
                .map(r ->{
                            System.out.println("NEW USER ADAPTER Mapper: " + r);
                            return r;
                        });
    }

    @Override
    public Optional<User> findById(Long id) {
        return this.userRepository.findById(id).map(userMapper::toDomain);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return this.userRepository.findByUsername(username).map(userMapper::toDomain);
    }

    @Transactional
    @Override
    public void deleteByUsername(String username) {
        this.userRepository.deleteByUsername(username);
    }
}
