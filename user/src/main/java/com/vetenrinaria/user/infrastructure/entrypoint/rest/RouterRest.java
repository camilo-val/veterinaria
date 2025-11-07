package com.vetenrinaria.user.infrastructure.entrypoint.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
@RequiredArgsConstructor
public class RouterRest {

    private final String BASE = "/api/user-service";
    private final WebHandlerException exceptionHandler;

    @Bean
    public RouterFunction<ServerResponse> ruoter(Handler handler){
        return RouterFunctions.route(RequestPredicates.POST(this.BASE),handler::create)
                .filter(exceptionHandler);
    }
}
