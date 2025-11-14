package com.vetenrinaria.booking.domain.model.booking.gateway;

import com.vetenrinaria.booking.domain.model.booking.Booking;

import java.util.List;
import java.util.Optional;

public interface BookingGateway {
    Optional<Booking> findById(Long id);
    Optional<Booking> saveForClient(Booking booking);
    Optional<Booking> saveForEmployee(Booking booking);
    List<Booking> findByClientId(Long clientID);
    List<Booking> findByEmployeeId(Long employeeID);
    List<Booking> findByProductId(Long employeeID);
    List<Booking> findAll();
}
