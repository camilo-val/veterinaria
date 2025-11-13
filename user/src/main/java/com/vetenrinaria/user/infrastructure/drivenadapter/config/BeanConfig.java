package com.vetenrinaria.user.infrastructure.drivenadapter.config;

import com.vetenrinaria.user.domain.model.gateway.PasswordEncoderGateway;
import com.vetenrinaria.user.domain.model.gateway.UserGateway;
import com.vetenrinaria.user.domain.usecase.DeleteUser;
import com.vetenrinaria.user.domain.usecase.FindUser;
import com.vetenrinaria.user.domain.usecase.SaveUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class BeanConfig {

    @Bean
    public SaveUser saveUser(UserGateway userGateway, PasswordEncoderGateway passwordEncoderGateway) {
        return new SaveUser(userGateway , passwordEncoderGateway);

    }

    @Bean
    public FindUser findUser(UserGateway userGateway){
        return new FindUser(userGateway);
    }

    @Bean
    public DeleteUser deleteUser(UserGateway userGateway){
        return new DeleteUser(userGateway);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
