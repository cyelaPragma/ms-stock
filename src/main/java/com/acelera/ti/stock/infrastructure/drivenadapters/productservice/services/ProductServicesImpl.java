package com.acelera.ti.stock.infrastructure.drivenadapters.productservice.services;

import com.acelera.ti.stock.domain.model.gateways.services.ProductServices;
import com.acelera.ti.stock.domain.model.model.product.Product;
import com.acelera.ti.stock.infrastructure.drivenadapters.productservice.feigmClient.ProductFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductServicesImpl implements ProductServices {
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
