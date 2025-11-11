package com.vetenrinaria.products.domain.model.gateway;

import com.vetenrinaria.products.domain.model.Product;

import java.util.Optional;

public interface ProductGateway {
    Optional<Product> findById(Long id);
    Optional<Product> findByProductName(String name);
    Optional<Product> save(Product product);
    void deleteById(Long id);
}
