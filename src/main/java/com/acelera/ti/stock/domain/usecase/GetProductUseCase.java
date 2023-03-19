package com.acelera.ti.stock.domain.usecase;

import com.acelera.ti.stock.domain.model.exceptions.NotExistProductsException;
import com.acelera.ti.stock.domain.model.exceptions.ProductNotFoundException;
import com.acelera.ti.stock.domain.model.gateways.services.ProductServices;
import com.acelera.ti.stock.domain.model.model.product.Product;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class GetProductUseCase {
    private final ProductServices productServices;
    public Product action(Long id) {
        Product product = productServices.getProductById(id);
        if (product == null) {
            throw new ProductNotFoundException();
        }
        return product;
    }
}
