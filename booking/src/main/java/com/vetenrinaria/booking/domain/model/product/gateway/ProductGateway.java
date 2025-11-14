package com.vetenrinaria.booking.domain.model.product.gateway;

import com.vetenrinaria.booking.domain.model.product.Product;

import java.util.Optional;

public interface ProductGateway {
    Optional<Product> findByID(Long productId);
    Optional<Product> updateStock(Long productId);
}
