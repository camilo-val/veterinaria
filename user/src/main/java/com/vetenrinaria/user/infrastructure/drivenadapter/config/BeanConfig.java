package com.vetenrinaria.user.infrastructure.drivenadapter.config;

import com.vetenrinaria.user.domain.model.gateway.UserGateway;
import com.vetenrinaria.user.domain.usecase.DeleteUser;
import com.vetenrinaria.user.domain.usecase.FindUser;
import com.vetenrinaria.user.domain.usecase.SaveUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public SaveUser saveUser(UserGateway userGateway){
        return new SaveUser(userGateway);

    }

    @Bean
    public FindUser findUser(UserGateway userGateway){
        return new FindUser(userGateway);
    }

    @Bean
    public DeleteUser deleteUser(UserGateway userGateway){
        return new DeleteUser(userGateway);
    }
}
