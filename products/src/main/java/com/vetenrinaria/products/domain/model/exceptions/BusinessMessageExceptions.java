package com.vetenrinaria.products.domain.model.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BusinessMessageExceptions {
    PRODUCT_NOT_EXIST("the product not exist", "MSP-0001"),
    PRODUCT_EXIST("the product exist", "MSP-0002"),
    PRODUCT_INVALID("the product not is valid", "MSP-0003");

    private final String message;
    private final String code;
}
