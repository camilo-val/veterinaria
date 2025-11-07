package com.vetenrinaria.user.infrastructure.drivenadapter.config;

import com.vetenrinaria.user.domain.model.gateway.UserGateway;
import com.vetenrinaria.user.domain.usecase.SaveUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public SaveUser saveUser(UserGateway userGateway){
        return new SaveUser(userGateway);

    }

}
