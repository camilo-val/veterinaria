package com.vetenrinaria.user.infrastructure.drivenadapter.mapper;

import com.vetenrinaria.user.domain.model.Role;
import com.vetenrinaria.user.domain.model.User;
import com.vetenrinaria.user.infrastructure.drivenadapter.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ObjectFactory;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    // Mapeo de dominio a entidad
    default UserEntity toEntity(User user) {
        return UserEntity.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .role(user.getRole().name())
                .status(user.getStatus())
                .createAt(user.getCreateAt())
                .updateAt(user.getUpdateAt())
                .build();
    }

    // Mapeo de entidad a dominio usando método de fábrica
    @ObjectFactory
    default User toDomain(UserEntity entity) {
        return User.createUser(
                entity.getId(),
                entity.getUsername(),
                entity.getPassword(),
                Role.valueOf(entity.getRole())
        );
    }
}
