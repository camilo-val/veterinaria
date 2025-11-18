package com.vetenrinaria.booking.domain.usecase;

import com.vetenrinaria.booking.domain.model.booking.Booking;
import com.vetenrinaria.booking.domain.model.booking.BookingWithData;
import com.vetenrinaria.booking.domain.model.booking.gateway.BookingGateway;
import com.vetenrinaria.booking.domain.model.exceptions.BusinessExceptions;
import com.vetenrinaria.booking.domain.model.exceptions.BusinessMessageExceptions;
import com.vetenrinaria.booking.domain.model.person.PersonWithUser;
import com.vetenrinaria.booking.domain.model.person.gateway.PersonGateway;
import com.vetenrinaria.booking.domain.model.product.gateway.ProductGateway;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class BookingUseCase {

    private final BookingGateway  bookingGateway;
    private final PersonGateway personGateway;
    private final ProductGateway productGateway;

    public Optional<BookingWithData> createByClient(Booking booking) {
        return this.productGateway.findByID(booking.getProductId())
                .map(productExist -> this.personGateway.findByID(booking.getEmployeeId())
                .map(employeeBd -> {
                    Booking newBooking = Booking.createBooking(booking.getId(),
                            booking.getClientId(),
                            booking.getEmployeeId(),
                            booking.getProductId(),
                            booking.getInitialAt(),
                            booking.getFinalAt(),
                            booking.getStatus());
                    PersonWithUser clientBd = this.personGateway.findByID(booking.getClientId()).orElseThrow(()
                            -> new BusinessExceptions(BusinessMessageExceptions.INVALID_CLIENT));
                    return new BookingWithData(clientBd, employeeBd, productExist, this.bookingGateway
                            .saveForClient(newBooking).get());
                }).orElseThrow(() -> new BusinessExceptions(BusinessMessageExceptions.INVALID_BOOKING)));
    }

    public Optional<BookingWithData> createByEmployee(Booking booking) {
        return this.productGateway.findByID(booking.getProductId())
                .map(productExist -> this.personGateway.findByID(booking.getClientId())
                        .map(clientBd -> {
                            Booking newBooking = Booking.createBooking(booking.getId(),
                                    booking.getClientId(),
                                    booking.getEmployeeId(),
                                    booking.getProductId(),
                                    booking.getInitialAt(),
                                    booking.getFinalAt(),
                                    booking.getStatus());
                            PersonWithUser employeeBd = this.personGateway.findByID(booking.getEmployeeId()).orElseThrow(()
                                    -> new BusinessExceptions(BusinessMessageExceptions.INVALID_CLIENT));

                            return new BookingWithData(clientBd, employeeBd, productExist, this.bookingGateway
                                    .saveForClient(newBooking).get());
                        }).orElseThrow(() -> new BusinessExceptions(BusinessMessageExceptions.INVALID_BOOKING)));
    }
}
