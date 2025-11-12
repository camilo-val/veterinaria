package com.vetenrinaria.pet.infrastructure.entrypoint.imperativerest.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.*;

@Configuration
@RequiredArgsConstructor
public class RouterRest {
    private final WebHandlerException webHandlerException;
    private final String BASE = "/api/pet-service";

    @Bean
    public RouterFunction<ServerResponse> router(Handler handler){
        return RouterFunctions.route(RequestPredicates.POST(BASE), handler::save)
                .andRoute(RequestPredicates.DELETE(BASE + "{id}"), handler::delete)
                .andRoute(RequestPredicates.PUT(BASE + "{id}"), handler::update)
                .andRoute(RequestPredicates.GET(BASE + "{id}"), handler::findById)
                .andRoute(RequestPredicates.GET(BASE + "/personid/{personid}"), handler::findByPersonId)
                .andRoute(RequestPredicates.GET(BASE), handler::findall)
                .andRoute(RequestPredicates.GET(BASE + "/name/{name}"), handler::findByName);

    }
}
