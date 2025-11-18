package com.vetenrinaria.booking.infrastructure.drivenadapter.restconsumer.person.rest;

import com.vetenrinaria.booking.domain.model.product.Product;
import com.vetenrinaria.booking.domain.model.product.gateway.ProductGateway;
import com.vetenrinaria.booking.infrastructure.drivenadapter.restconsumer.person.dto.ProductResponse;
import com.vetenrinaria.booking.infrastructure.drivenadapter.restconsumer.person.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class WebClientProduct implements ProductGateway {
    @Value("${external-api.product.service-url}")
    private String url;
    private final WebClient.Builder webClient;
    private final ProductMapper productMapper;

    public Optional<Product> findByID(Long id) {
        return this.webClient.baseUrl(url)
                .build()
                .get()
                .uri("/api/product-service/{id}", id)
                .retrieve()
                .bodyToMono(ProductResponse.class)
                .map(productMapper::toDomain)
                .blockOptional();
    }

    @Override
    public List<Product> findAll() {
        return this.webClient.baseUrl(url)
                .build()
                .get()
                .uri("/api/product-service")
                .retrieve()
                .bodyToFlux(ProductResponse.class)
                .map(productMapper::toDomain)
                .collectList()
                .block();
    }

    @Override
    public Optional<Product> updateStock(Long productId) {
        return Optional.empty();
    }
}
