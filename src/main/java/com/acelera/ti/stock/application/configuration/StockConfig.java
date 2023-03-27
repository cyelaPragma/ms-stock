package com.acelera.ti.stock.application.configuration;

import com.acelera.ti.stock.domain.model.gateways.services.ProductServices;
import com.acelera.ti.stock.domain.usecase.GetAllProductsUseCase;
import com.acelera.ti.stock.domain.usecase.GetProductUseCase;
import com.acelera.ti.stock.infrastructure.drivenadapters.productservice.ProductFeignClient;
import com.acelera.ti.stock.infrastructure.drivenadapters.productservice.ProductServicesImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StockConfig {
    @Bean
    public ProductServices productServices(ProductFeignClient productFeignClient) {
        return new ProductServicesImpl(productFeignClient);
    }

    @Bean
    public GetAllProductsUseCase getAllProductsUseCase(ProductServices productServices){
        return new GetAllProductsUseCase(productServices);
    }

    @Bean
    public GetProductUseCase getProductUseCase(ProductServices productServices){
        return new GetProductUseCase(productServices);
    }
}
