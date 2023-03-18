package com.acelera.ti.stock.domain.model.gateways.services;

import com.acelera.ti.stock.domain.model.model.product.Product;

import java.util.List;

public interface ProductServices {
    List<Product> getAllProducts();
    Product getProductById(Long id);
}
