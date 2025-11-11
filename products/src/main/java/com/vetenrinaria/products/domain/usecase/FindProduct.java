package com.vetenrinaria.products.domain.usecase;

import com.vetenrinaria.products.domain.model.Product;
import com.vetenrinaria.products.domain.model.exceptions.BusinessExceptions;
import com.vetenrinaria.products.domain.model.exceptions.BusinessMessageExceptions;
import com.vetenrinaria.products.domain.model.gateway.ProductGateway;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class FindProduct {
    private final ProductGateway productGateway;

    public Optional<Product> findById(Long id) {
        return this.productGateway.findById(id)
                .or(() -> {
                    throw new BusinessExceptions(BusinessMessageExceptions.PRODUCT_NOT_EXIST);
                });
    }

    public Optional<Product> findByProductName(String productName){
        return this.productGateway.findByProductName(productName)
                .or(() -> {
                    throw new BusinessExceptions(BusinessMessageExceptions.PRODUCT_NOT_EXIST);
                });

    }

}
