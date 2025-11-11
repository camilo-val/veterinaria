package com.vetenrinaria.products.infrastructure.mapper;

import com.vetenrinaria.products.domain.model.Product;
import com.vetenrinaria.products.infrastructure.drivenadapter.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ObjectFactory;

@Mapper(componentModel = "spring")
public interface ProductAdapterMapper {

    // Entity → Domain
    Product toDomain(ProductEntity entity);

    default ProductEntity toEntity(Product product) {
        if (product == null) return null;

        return ProductEntity.builder()
                .id(product.getId())
                .productName(product.getProductName())
                .description(product.getDescription())
                .stock(product.getStock())
                .build();
    }

    @ObjectFactory
    default Product createProduct(ProductEntity entity) {
        return Product.newProduct(
                entity.getId(),
                entity.getProductName(),
                entity.getDescription(),
                entity.getStock()
        );
    }
}
