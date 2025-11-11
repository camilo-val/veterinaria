package com.vetenrinaria.products.domain.usecase;

import com.vetenrinaria.products.domain.model.Product;
import com.vetenrinaria.products.domain.model.exceptions.BusinessExceptions;
import com.vetenrinaria.products.domain.model.exceptions.BusinessMessageExceptions;
import com.vetenrinaria.products.domain.model.gateway.ProductGateway;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class ProductUseCase {
    private final ProductGateway productGateway;

    public Optional<Product> create (Product product){
        System.out.println("ENTRE 0: " + product.toString());
        Optional<Product> productBd = this.productGateway.findByProductName(product.getProductName());
        if (productBd.isPresent()){
            System.out.println("ENTRE 1: " + product.toString());
            if (productBd.get().getProductName().equals(product.getProductName())){
                System.out.println("ENTRE 2: " + product.toString());

                throw new BusinessExceptions(BusinessMessageExceptions.PRODUCT_EXIST);
            }
        }
        System.out.println("ENTRE 3: " + product.toString());
        Product newProduct = Product.newProduct(product.getId(), product.getProductName(), product.getDescription(), product.getStock());
        System.out.println("ENTRE 4: " + newProduct.toString());
        return this.productGateway.save(newProduct);
    }

    public Optional<Product> update(Long id, Product product){
        Optional<Product> productFindByProductName = this.productGateway.findByProductName(product.getProductName());
        return this.productGateway.findById(id).map(productById ->{
            if (productFindByProductName.isPresent() && !productFindByProductName.get().getProductName().equals(product.getProductName())){
                throw new BusinessExceptions(BusinessMessageExceptions.PRODUCT_EXIST);
            }
            Product updateProduct = Product.newProduct(id,product.getProductName(), product.getDescription(), product.getStock());
            return this.productGateway.save(updateProduct);
        }).orElseThrow(() -> new BusinessExceptions(BusinessMessageExceptions.PRODUCT_NOT_EXIST));
    }
}
