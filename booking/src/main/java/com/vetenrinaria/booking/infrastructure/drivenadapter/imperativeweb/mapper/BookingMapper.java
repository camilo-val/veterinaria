package com.vetenrinaria.booking.infrastructure.drivenadapter.imperativeweb.mapper;

import com.vetenrinaria.booking.domain.model.booking.Booking;
import com.vetenrinaria.booking.domain.model.booking.BookingWithData;
import com.vetenrinaria.booking.infrastructure.drivenadapter.imperativeweb.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.ObjectFactory;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BookingMapper {

    BookingMapper INSTANCE = Mappers.getMapper(BookingMapper.class);

    // -------------------------------
    // BookingWithData -> BookingResponse
    // -------------------------------
    @ObjectFactory
    default BookingResponse toResponse(BookingWithData booking){
        return new BookingResponse(booking.getBooking().getId(),
                new PersonResponse(booking.getClient().getPerson().getId(),
                        booking.getClient().getPerson().getName(),
                        booking.getClient().getPerson().getEmail(),
                        new UserResponse(booking.getClient().getUser().getId(),
                                booking.getClient().getUser().getUsername(),
                                booking.getClient().getUser().getRole(),
                                booking.getClient().getUser().getStatus())),
                new PersonResponse(booking.getEmployee().getPerson().getId(),
                        booking.getEmployee().getPerson().getName(),
                        booking.getEmployee().getPerson().getEmail(),
                        new UserResponse(booking.getEmployee().getUser().getId(),
                                booking.getEmployee().getUser().getUsername(),
                                booking.getEmployee().getUser().getRole(),
                                booking.getEmployee().getUser().getStatus())),
                new ProductResponse(booking.getProduct().getId(),
                        booking.getProduct().getProductName(),
                        booking.getProduct().getDescription()),
                booking.getBooking().getInitialAt(),
                booking.getBooking().getFinalAt(),
                booking.getBooking().getStatus()
                );

    }
    default Booking toDomain(BookingRequest request) {
        return Booking.createBooking(
                request.getId(),
                request.getClientId(),
                request.getEmployeeId(),
                request.getProductId(),
                request.getInitialAt(),
                request.getFinalAt(),
                request.getStatus()
        );
    }
}