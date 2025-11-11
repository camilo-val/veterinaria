package com.vetenrinaria.products.infrastructure.entrypoint.config;

import com.vetenrinaria.products.domain.model.gateway.ProductGateway;
import com.vetenrinaria.products.domain.usecase.DeleteProduct;
import com.vetenrinaria.products.domain.usecase.FindProduct;
import com.vetenrinaria.products.domain.usecase.ProductUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public ProductUseCase productUseCase(ProductGateway productGateway){
        return new ProductUseCase(productGateway);
    }

    @Bean
    public FindProduct findProduct(ProductGateway productGateway){
        return new FindProduct(productGateway);
    }

    @Bean
    public DeleteProduct deleteProduct(ProductGateway productGateway){
        return new DeleteProduct(productGateway);
    }
}
