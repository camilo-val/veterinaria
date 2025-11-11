package com.vetenrinaria.person.infrastructure.entrypoint.restconsumer.rest;

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

    private final String BASE = "/api/person-service";
    private final WebHandlerException exceptionHandler;

    @Bean
    public RouterFunction<ServerResponse> ruoter(Handler handler){
        return RouterFunctions.route(RequestPredicates.POST(this.BASE),handler::create)
                .andRoute(RequestPredicates.GET(BASE+"/{id}"),handler::findById)
                .andRoute(RequestPredicates.GET(BASE+"/email/{email}"),handler::findByEmail)
                .andRoute(RequestPredicates.PUT(BASE+"/{id}"),handler::update)
                .andRoute(RequestPredicates.PUT(BASE+"/{id}"),handler::delete);
                //.filter(exceptionHandler);
    }
}
