package com.vetenrinaria.booking.infrastructure.drivenadapter.mysql.mapper;

import com.vetenrinaria.booking.domain.model.booking.Booking;
import com.vetenrinaria.booking.infrastructure.drivenadapter.mysql.entity.BookingEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ObjectFactory;

@Mapper(componentModel = "spring")
public interface BookingMapperAdapter {

    @ObjectFactory
    default Booking toDomain(BookingEntity bookingEntity){
        return Booking.createBooking(bookingEntity.getId(),
                bookingEntity.getClientId(),
                bookingEntity.getEmployeeId(),
                bookingEntity.getProductId(),
                bookingEntity.getInitialAt(),
                bookingEntity.getFinalAt(),
                bookingEntity.getStatus());
    }

    default BookingEntity toEntity(Booking booking){
        return new BookingEntity(booking.getId(),
                booking.getClientId(),
                booking.getEmployeeId(),
                booking.getProductId(),
                booking.getInitialAt(),
                booking.getFinalAt(),
                booking.getStatus());
    }
}
