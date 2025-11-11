package com.vetenrinaria.user.infrastructure.drivenadapter.mapper;

import com.vetenrinaria.user.domain.model.Role;
import com.vetenrinaria.user.domain.model.User;
import com.vetenrinaria.user.infrastructure.drivenadapter.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ObjectFactory;

@Mapper(componentModel = "spring")
public interface UserMapper {

    // Mapeo de dominio a entidad
    default UserEntity toEntity(User user) {
        System.out.println("ENTRE toEntity: " + user);

        return UserEntity.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .role(user.getRole().name())
                .status(user.getStatus())
                .createdAt(user.getCreatedAt())
                .updateAt(user.getUpdateAt())
                .build();
    }

    // Mapeo de entidad a dominio usando método de fábrica
    @ObjectFactory
    default User toDomain(UserEntity entity) {
        System.out.println("ENTRE toDomain: " + entity);
        return User.createUser(
                entity.getId(),
                entity.getUsername(),
                entity.getPassword(),
                Role.valueOf(entity.getRole()),
                entity.getStatus(),
                entity.getCreatedAt(),
                entity.getUpdateAt()
        );
    }
}
