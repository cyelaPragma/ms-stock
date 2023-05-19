package com.acelera.ti.stock.domain.usecase;

import com.acelera.ti.stock.domain.model.exceptions.NotExistProductsException;
import com.acelera.ti.stock.domain.model.gateways.services.ProductServices;
import com.acelera.ti.stock.domain.model.model.product.Product;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class GetAllProductsUseCase {
    private final ProductServices productServices;

    public List<Product> action() {
        List<Product> products = productServices.getAllProducts();
        if (products == null || products.isEmpty()) {
            throw new NotExistProductsException();
        }
        return products;
    }
}
