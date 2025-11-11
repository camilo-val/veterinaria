package com.vetenrinaria.products.domain.model;

import com.vetenrinaria.products.domain.model.exceptions.BusinessExceptions;
import com.vetenrinaria.products.domain.model.exceptions.BusinessMessageExceptions;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Product {
    private final Long id;
    private final String productName;
    private final String description;
    private final int stock;

    private Product(Long id, String productName, String description, int stock) {
        this.id = id;
        this.productName = productName;
        this.description = description;
        this.stock = stock;
    }

    public static Product newProduct(Long id, String productName, String description, int stock){
        if (productName == null || productName.isBlank() || stock == 0){
            throw new BusinessExceptions(BusinessMessageExceptions.PRODUCT_INVALID);
        }
        return new Product(id,productName,description,stock);
    }
}
