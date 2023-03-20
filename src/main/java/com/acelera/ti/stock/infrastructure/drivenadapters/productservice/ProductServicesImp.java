
package com.acelera.ti.stock.infrastructure.drivenadapters.productservice;

import com.acelera.ti.stock.domain.model.gateways.services.ProductServices;
import com.acelera.ti.stock.domain.model.model.product.Product;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ProductServicesImp implements ProductServices {
    private final ProductFeignClient productFeignClient;

    @Override
    public List<Product> getAllProducts() {
        return productFeignClient.getAllProducts();
    }

    @Override
    public Product getProductById(Long id) {
        return productFeignClient.findProductById(id);
    }
}


