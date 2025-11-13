package com.veterinaria.authentication.infrastructure.entrypoint.reactiveweb.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.*;

@Configuration
public class RouterRest {
    private final String BASE = "/api/authentication-service";

    @Bean
    public RouterFunction<ServerResponse> routerFunction(Handler handler){
        return RouterFunctions.route(RequestPredicates.POST(BASE), handler::login);

    }
}
