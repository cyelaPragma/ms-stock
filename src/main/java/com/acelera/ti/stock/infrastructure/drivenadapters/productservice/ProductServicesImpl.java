package com.acelera.ti.stock.infrastructure.drivenadapters.productservice;

import com.acelera.ti.stock.domain.model.gateways.services.ProductServices;
import com.acelera.ti.stock.domain.model.model.product.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServicesImpl implements ProductServices {

    private final ProductFeignClient productFeignClient;

    public ProductServicesImpl(ProductFeignClient productFeignClient) {
        this.productFeignClient = productFeignClient;
    }

    @Override
    public List<Product> getAllProducts() {
        return productFeignClient.getAllProducts();
    }

    @Override
    public Product getProductById(Long id) {
        return productFeignClient.findProductById(id);
    }
}
