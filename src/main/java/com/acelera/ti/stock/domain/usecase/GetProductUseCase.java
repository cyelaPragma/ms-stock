package com.acelera.ti.stock.domain.usecase;

import com.acelera.ti.stock.domain.model.exceptions.ProductNotFoundException;
import com.acelera.ti.stock.domain.model.gateways.services.ProductServices;
import com.acelera.ti.stock.domain.model.model.product.Product;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetProductUseCase {
    private final ProductServices productServices;

    public Product action(Long productId) {
        Product product = productServices.getProductById(productId);
        if (product == null) {
            throw new ProductNotFoundException();
        }
        return product;
    }
}


