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

    public BookingWithData(PersonWithUser client, PersonWithUser employee, Product product, Booking booking) {
        this.client = client;
        this.employee = employee;
        this.product = product;
        this.booking = booking;
    }
}
