package com.veterinaria.authentication.infrastructure.drivenadapter.restconsumer.mapper;

import com.veterinaria.authentication.domain.model.Token;
import com.veterinaria.authentication.domain.model.User;
import com.veterinaria.authentication.infrastructure.drivenadapter.restconsumer.dto.UserResponse;
import com.veterinaria.authentication.infrastructure.drivenadapter.restconsumer.model.TokenModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ObjectFactory;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TokenAdapterMapper {

    @ObjectFactory
    default Token toDomain(TokenModel tokenModel){
        return Token.createToken(tokenModel.getToken(),tokenModel.getExpirationAt());
    }
}
