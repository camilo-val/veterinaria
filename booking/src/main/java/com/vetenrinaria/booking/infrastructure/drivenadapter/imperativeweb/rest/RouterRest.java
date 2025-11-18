package com.vetenrinaria.booking.infrastructure.drivenadapter.imperativeweb.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
public class RouterRest {

    private final String BASE = "/api/booking-service";

    @Bean
    public RouterFunction<ServerResponse> route(Handler handler){
        return RouterFunctions.route(RequestPredicates.POST(BASE + "/create-client"), handler::createByClient)
                .andRoute(RequestPredicates.POST(BASE + "/create-employee"), handler::createByClient)
                .andRoute(RequestPredicates.GET(BASE),handler::findAll)
                .andRoute(RequestPredicates.GET(BASE + "/{id}"), handler::findById)
                .andRoute(RequestPredicates.GET(BASE + "/client/{clientid}"), handler::findByClientId)
                .andRoute(RequestPredicates.GET(BASE + "/employee/{employeeid}"), handler::findByEmployeeId)
                .andRoute(RequestPredicates.GET(BASE + "/product/{productid}"), handler::findByProductId);
    }
}
