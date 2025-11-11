package com.vetenrinaria.products.infrastructure.drivenadapter.data;

import com.vetenrinaria.products.infrastructure.drivenadapter.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProductRepository extends CrudRepository<ProductEntity, Long> {
    Optional<ProductEntity> findByProductName(String name);
}
