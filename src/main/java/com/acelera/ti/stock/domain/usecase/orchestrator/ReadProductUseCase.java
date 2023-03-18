package com.acelera.ti.stock.domain.usecase.orchestrator;

import com.acelera.ti.stock.domain.model.gateways.services.ProductServices;
import com.acelera.ti.stock.domain.model.model.product.Product;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ReadProductUseCase {
    private final ProductServices productServices;

    public List<Product> getAllProducts() {
        return productServices.getAllProducts();
    }

    public Product getProductById(Long id){
        return productServices.getProductById(id);
    }
}
