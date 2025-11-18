package com.vetenrinaria.booking.infrastructure.drivenadapter.mysql.adapter;

import com.vetenrinaria.booking.domain.model.booking.Booking;
import com.vetenrinaria.booking.domain.model.booking.gateway.BookingGateway;
import com.vetenrinaria.booking.infrastructure.drivenadapter.mysql.data.BookingData;
import com.vetenrinaria.booking.infrastructure.drivenadapter.mysql.mapper.BookingMapperAdapter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
public class BookingAdapter implements BookingGateway {
    private final BookingData  bookingData;
    private final BookingMapperAdapter bookingMapperAdapter;

    @Override
    public Optional<Booking> findById(Long id) {
        return this.bookingData.findById(id)
                .map(bookingMapperAdapter::toDomain);
    }

    @Override
    public Optional<Booking> saveForClient(Booking booking) {
        return Optional.of(this.bookingData.save(bookingMapperAdapter.toEntity(booking)))
                .map(bookingMapperAdapter::toDomain);
    }

    @Override
    public Optional<Booking> saveForEmployee(Booking booking) {
        return Optional.of(this.bookingData.save(bookingMapperAdapter.toEntity(booking)))
                .map(bookingMapperAdapter::toDomain);
    }

    @Override
    public List<Booking> findByClientId(Long clientID) {
        return StreamSupport.stream(this.bookingData.findByClientId(clientID).spliterator(), false)
                .map(bookingMapperAdapter::toDomain)
                .toList();
    }

    @Override
    public List<Booking> findByEmployeeId(Long employeeID) {
        return StreamSupport.stream(this.bookingData.findByEmployeeId(employeeID).spliterator(), false)
                .map(bookingMapperAdapter::toDomain)
                .toList();
    }

    @Override
    public List<Booking> findByProductId(Long productID) {
        return StreamSupport.stream(this.bookingData.findByProductId(productID).spliterator(), false)
                .map(bookingMapperAdapter::toDomain)
                .toList();
    }

    @Override
    public List<Booking> findAll() {
        return StreamSupport.stream(this.bookingData.findAll().spliterator(), false)
                .map(bookingMapperAdapter::toDomain)
                .toList();    }
}
