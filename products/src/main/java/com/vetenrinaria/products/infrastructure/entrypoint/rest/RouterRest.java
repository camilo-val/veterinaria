package com.vetenrinaria.products.infrastructure.entrypoint.rest;

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

    private final String BASE = "/api/product-service";
    private final WebHandlerException webHandlerException;

    @Bean
    public RouterFunction<ServerResponse> router(Handler handler){
        return RouterFunctions.route(RequestPredicates.POST(BASE),handler::create)
                .andRoute(RequestPredicates.PUT(BASE + "/{id}"),handler::update)
                .andRoute(RequestPredicates.DELETE(BASE + "/{id}"), handler::deleteById)
                .andRoute(RequestPredicates.GET(BASE + "/{id}"),handler::findById)
                .andRoute(RequestPredicates.GET(BASE + "/productname/{productname}"),handler::findByProductName);
                //.filter(webHandlerException);

    }
}
