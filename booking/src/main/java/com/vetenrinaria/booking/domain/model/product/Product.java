package com.vetenrinaria.booking.domain.model.product;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Product {
    private final Long id;
    private final String productName;
    private final String description;
    private final int stock;

    public Product(Long id, String productName, String description, int stock) {
        this.id = id;
        this.productName = productName;
        this.description = description;
        this.stock = stock;
    }

}
