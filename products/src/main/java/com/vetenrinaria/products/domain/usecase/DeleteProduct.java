package com.vetenrinaria.products.domain.usecase;

import com.vetenrinaria.products.domain.model.exceptions.BusinessExceptions;
import com.vetenrinaria.products.domain.model.exceptions.BusinessMessageExceptions;
import com.vetenrinaria.products.domain.model.gateway.ProductGateway;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteProduct {
    private final ProductGateway productGateway;

    public void deleteByProductName(Long id){
        this.productGateway.findById(id)
                .map(delete -> {
                    this.productGateway.deleteById(delete.getId());
                    return delete;
                })
                .orElseThrow(() -> new BusinessExceptions(BusinessMessageExceptions.PRODUCT_NOT_EXIST));
    }
}
