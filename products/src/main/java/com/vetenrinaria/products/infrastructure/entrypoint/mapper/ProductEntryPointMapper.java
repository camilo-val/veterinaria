package com.vetenrinaria.products.infrastructure.entrypoint.mapper;

import com.vetenrinaria.products.domain.model.Product;
import com.vetenrinaria.products.infrastructure.entrypoint.dto.ProductRequest;
import com.vetenrinaria.products.infrastructure.entrypoint.dto.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ObjectFactory;

@Mapper(componentModel = "spring")
public interface ProductEntryPointMapper {

    // DTO → Dominio
    Product toDomain(ProductRequest request);

    // Dominio → DTO
    default ProductResponse toResponse(Product product) {
        if (product == null) return null;

        return ProductResponse.builder()
                .id(product.getId())
                .productName(product.getProductName())
                .description(product.getDescription())
                .stock(product.getStock())
                .build();
    }

    // Método de fábrica para construir el dominio usando el método static
    @ObjectFactory
    default Product createProduct(ProductRequest request) {
        return Product.newProduct(
                request.getId(),
                request.getProductName(),
                request.getDescription(),
                request.getStock()
        );
    }
}
