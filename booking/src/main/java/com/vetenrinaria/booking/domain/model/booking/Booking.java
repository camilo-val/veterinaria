package com.vetenrinaria.booking.domain.model.booking;

import com.vetenrinaria.booking.domain.model.exceptions.BusinessExceptions;
import com.vetenrinaria.booking.domain.model.exceptions.BusinessMessageExceptions;
import lombok.Getter;

import java.time.Instant;

@Getter
public class Booking {
    private final Long id;
    private final Long clientId;
    private final Long employeeId;
    private final Long productId;
    private final Instant initialAt;
    private final Instant finalAt;
    private final String status;

    private Booking(Long id, Long clientId, Long employeeId, Long productId, Instant initialDate, Instant finalAt, String status) {
        this.id = id;
        this.clientId = clientId;
        this.employeeId = employeeId;
        this.productId = productId;
        this.initialAt = initialDate;
        this.finalAt = finalAt;
        this.status = status;
    }

    public static Booking createBooking(Long id, Long clientId, Long employeeId, Long productId, Instant initialAt, Instant finalAt, String status){
        if (clientId == null || employeeId == null || productId == null || initialAt == null || finalAt == null || status == null){
            throw new BusinessExceptions(BusinessMessageExceptions.INVALID_BOOKING);
        }
        return new Booking(id, clientId, employeeId, productId, initialAt, finalAt, status);
    }
}
