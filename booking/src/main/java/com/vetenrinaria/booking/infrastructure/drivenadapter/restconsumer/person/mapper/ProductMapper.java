package com.vetenrinaria.booking.infrastructure.drivenadapter.restconsumer.person.mapper;

import com.vetenrinaria.booking.domain.model.person.Person;
import com.vetenrinaria.booking.domain.model.product.Product;
import com.vetenrinaria.booking.infrastructure.drivenadapter.restconsumer.person.dto.PersonResponse;
import com.vetenrinaria.booking.infrastructure.drivenadapter.restconsumer.person.dto.ProductResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    default Product toDomain(ProductResponse productResponse) {
        return new Product(productResponse.getId(),
                productResponse.getProductName(),
                productResponse.getDescription(),
                productResponse.getStock());
    }
}
