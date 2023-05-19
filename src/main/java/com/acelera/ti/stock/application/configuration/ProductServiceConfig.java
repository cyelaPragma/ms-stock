package com.acelera.ti.stock.application.configuration;

import com.acelera.ti.stock.domain.model.gateways.services.ProductServices;
import com.acelera.ti.stock.infrastructure.drivenadapters.productservice.feigmClient.ProductFeignClient;
import com.acelera.ti.stock.infrastructure.drivenadapters.productservice.services.ProductServicesImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductServiceConfig {
    @Bean
    public ProductServices productServices(ProductFeignClient productFeignClient) {
        return new ProductServicesImpl(productFeignClient);
    }
}

