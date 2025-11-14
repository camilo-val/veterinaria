package com.vetenrinaria.booking.domain.model.booking;

import com.vetenrinaria.booking.domain.model.person.PersonWithUser;
import com.vetenrinaria.booking.domain.model.product.Product;
import lombok.Getter;

@Getter
public class BookingWithData {
    private final PersonWithUser client;
    private final PersonWithUser employee;
    private final Product product;
    private final Booking booking;


}
