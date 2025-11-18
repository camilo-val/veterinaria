package com.vetenrinaria.booking.domain.model.product.gateway;

import com.vetenrinaria.booking.domain.model.product.Product;

import java.util.List;
import java.util.Optional;

public interface ProductGateway {
    Optional<Product> findByID(Long productId);
    List<Product> findAll();
    Optional<Product> updateStock(Long productId);
}
