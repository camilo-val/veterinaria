package com.vetenrinaria.booking.infrastructure.drivenadapter.imperativeweb.config;

import com.vetenrinaria.booking.domain.model.booking.gateway.BookingGateway;
import com.vetenrinaria.booking.domain.model.person.gateway.PersonGateway;
import com.vetenrinaria.booking.domain.model.product.gateway.ProductGateway;
import com.vetenrinaria.booking.domain.usecase.BookingUseCase;
import com.vetenrinaria.booking.domain.usecase.FindBooking;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public BookingUseCase bookingUseCase(BookingGateway bookingGateway, PersonGateway personGateway, ProductGateway productGateway){
        return new BookingUseCase(bookingGateway,personGateway,productGateway);
    }

    @Bean
    public FindBooking findBooking(BookingGateway bookingGateway, PersonGateway personGateway, ProductGateway productGateway){
        return new FindBooking(bookingGateway,personGateway,productGateway);
    }
}
