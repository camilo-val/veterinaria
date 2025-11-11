package com.vetenrinaria.products.infrastructure.entrypoint.rest;

import com.vetenrinaria.products.domain.usecase.DeleteProduct;
import com.vetenrinaria.products.domain.usecase.FindProduct;
import com.vetenrinaria.products.domain.usecase.ProductUseCase;
import com.vetenrinaria.products.infrastructure.entrypoint.dto.ProductRequest;
import com.vetenrinaria.products.infrastructure.entrypoint.mapper.ProductEntryPointMapper;
import jakarta.servlet.ServletException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class Handler {
    private final ProductEntryPointMapper productEntryPointMapper;
    private final ProductUseCase productUseCase;
    private final DeleteProduct deleteProduct;
    private final FindProduct findProduct;

    public ServerResponse create(ServerRequest request) throws ServletException, IOException {
        ProductRequest body = request.body(ProductRequest.class);
        System.out.println("REQUEST: " + body.toString());
        return this.productUseCase.create(productEntryPointMapper.toDomain(body))
                .map(productEntryPointMapper::toResponse)
                .map(ServerResponse.created(request.uri())::body)
                .get();
    }

    public ServerResponse update(ServerRequest request) throws ServletException, IOException {
        Long id = Long.valueOf(request.pathVariable("id"));
        ProductRequest body = request.body(ProductRequest.class);

        return this.productUseCase.update(id, productEntryPointMapper.toDomain(body))
                .map(productEntryPointMapper::toResponse)
                .map(ServerResponse.ok()::body)
                .get();
    }

    public ServerResponse findById(ServerRequest request){
        return this.findProduct.findById(Long.valueOf(request.pathVariable("id")))
                .map(productEntryPointMapper::toResponse)
                .map(ServerResponse.ok()::body)
                .get();
    }

    public ServerResponse findByProductName(ServerRequest request){
        return this.findProduct.findByProductName(request.pathVariable("productname"))
                .map(productEntryPointMapper::toResponse)
                .map(ServerResponse.ok()::body)
                .get();
    }

    public ServerResponse deleteById(ServerRequest request){
        this.deleteProduct.deleteByProductName(Long.valueOf(request.pathVariable("id")));
        return ServerResponse.noContent().build();
    }
}
