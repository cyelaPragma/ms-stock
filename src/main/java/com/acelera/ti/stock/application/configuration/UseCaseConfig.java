package com.acelera.ti.stock.application.configuration;

import com.acelera.ti.stock.domain.model.gateways.services.ProductServices;
import com.acelera.ti.stock.domain.usecase.GetAllProductsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class UseCaseConfig {
    @Bean
    public GetAllProductsUseCase getAllProductsUseCase(ProductServices productServices) {
        return new GetAllProductsUseCase(productServices);
    }
}
