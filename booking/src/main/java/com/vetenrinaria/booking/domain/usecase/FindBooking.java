package com.vetenrinaria.booking.domain.usecase;

import com.vetenrinaria.booking.domain.model.booking.BookingWithData;
import com.vetenrinaria.booking.domain.model.booking.gateway.BookingGateway;
import com.vetenrinaria.booking.domain.model.exceptions.BusinessExceptions;
import com.vetenrinaria.booking.domain.model.exceptions.BusinessMessageExceptions;
import com.vetenrinaria.booking.domain.model.person.gateway.PersonGateway;
import com.vetenrinaria.booking.domain.model.product.gateway.ProductGateway;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class FindBooking {
    private final BookingGateway bookingGateway;
    private final PersonGateway personGateway;
    private final ProductGateway productGateway;

    public Optional<BookingWithData> findByIdBooking(Long id) {
        return this.bookingGateway.findById(id)
            .flatMap(booking -> personGateway.findByID(booking.getClientId())
            .flatMap(client -> personGateway.findByID(booking.getEmployeeId())
            .flatMap(employee -> productGateway.findByID(booking.getProductId())
            .map(product -> new BookingWithData(client, employee, product, booking)))))
            .or(() -> {
                throw new BusinessExceptions(BusinessMessageExceptions.INVALID_BOOKING);
            });
    }

    public List<BookingWithData> findByClientId(Long clientId) {
        return this.bookingGateway.findByClientId(clientId)
                .stream().map(booking -> personGateway.findByID(booking.getClientId())
                        .flatMap(client -> personGateway.findByID(booking.getEmployeeId())
                                .flatMap(employee -> productGateway.findByID(booking.getProductId())
                                        .map(product -> new BookingWithData(client, employee, product, booking))))
                        .get()).toList();
    }

    public List<BookingWithData> findByEmployee(Long employeeId) {
        return this.bookingGateway.findByEmployeeId(employeeId)
                .stream().map(booking -> personGateway.findByID(booking.getClientId())
                        .flatMap(client -> personGateway.findByID(booking.getEmployeeId())
                                .flatMap(employee -> productGateway.findByID(booking.getProductId())
                                        .map(product -> new BookingWithData(client, employee, product, booking))))
                        .get()).toList();
    }

    public List<BookingWithData> findByProduct(Long productId) {
        return this.bookingGateway.findByProductId(productId)
                .stream().map(booking -> personGateway.findByID(booking.getClientId())
                        .flatMap(client -> personGateway.findByID(booking.getEmployeeId())
                                .flatMap(employee -> productGateway.findByID(booking.getProductId())
                                        .map(product -> new BookingWithData(client, employee, product, booking))))
                        .get()).toList();
    }

    public List<BookingWithData> findAll() {
        return this.bookingGateway.findAll()
                .stream().map(booking -> personGateway.findByID(booking.getClientId())
                        .flatMap(client -> personGateway.findByID(booking.getEmployeeId())
                                .flatMap(employee -> productGateway.findByID(booking.getProductId())
                                        .map(product -> new BookingWithData(client, employee, product, booking))))
                        .get()).toList();
    }

}
