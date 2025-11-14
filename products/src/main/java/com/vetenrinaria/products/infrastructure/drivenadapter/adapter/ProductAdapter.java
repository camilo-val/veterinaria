package com.vetenrinaria.products.infrastructure.drivenadapter.adapter;

import com.vetenrinaria.products.domain.model.Product;
import com.vetenrinaria.products.domain.model.gateway.ProductGateway;
import com.vetenrinaria.products.infrastructure.drivenadapter.data.ProductRepository;
import com.vetenrinaria.products.infrastructure.mapper.ProductAdapterMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
@Component
public class ProductAdapter implements ProductGateway {

    private final ProductRepository productRepository;
    private final ProductAdapterMapper productAdapterMapper;

    @Override
    public Optional<Product> findById(Long id) {
        return this.productRepository.findById(id)
                .map(productAdapterMapper::toDomain);
    }

    @Override
    public Optional<Product> findByProductName(String name) {
        return productRepository.findByProductName(name)
                .map(productAdapterMapper::toDomain);
    }

    @Override
    public Optional<Product> save(Product product) {
        System.out.println("ADAPTER 0: " +product.toString());

        return Optional.of(this.productRepository.save(productAdapterMapper.toEntity(product)))
                .map(r -> {
                    System.out.println("ADAPTER 1: " +r.toString());
                    return r;
                })
                .map(productAdapterMapper::toDomain);
    }

    @Override
    public List<Product> findAll() {
        return StreamSupport.stream(productRepository.findAll().spliterator(), false)
                .map(productAdapterMapper::toDomain).toList();
    }

    @Override
    public void deleteById(Long id) {
        this.productRepository.deleteById(id);
    }
}
