package com.vetenrinaria.booking.infrastructure.drivenadapter.mysql.data;

import com.vetenrinaria.booking.infrastructure.drivenadapter.mysql.entity.BookingEntity;
import org.springframework.data.repository.CrudRepository;

public interface BookingData extends CrudRepository<BookingEntity, Long> {
    Iterable<BookingEntity> findByClientId(Long clientId);
    Iterable<BookingEntity> findByEmployeeId(Long clientId);
    Iterable<BookingEntity> findByProductId(Long productId);
}
