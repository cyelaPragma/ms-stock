package com.acelera.ti.stock.application.configuration;

import com.acelera.ti.stock.domain.model.gateways.repositories.StockRepository;
import com.acelera.ti.stock.domain.model.gateways.services.ProductServices;
import com.acelera.ti.stock.domain.usecase.GetAllProductsUseCase;
import com.acelera.ti.stock.domain.usecase.GetAllStockUseCase;
import com.acelera.ti.stock.domain.usecase.GetProductUseCase;
import com.acelera.ti.stock.domain.usecase.GetStockUseCase;
import com.acelera.ti.stock.domain.usecase.SaveStockUseCase;
import com.acelera.ti.stock.infrastructure.drivenadapters.productservice.feigmClient.ProductFeignClient;
import com.acelera.ti.stock.infrastructure.drivenadapters.productservice.services.ProductServicesImpl;
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

    @Bean
    public SaveStockUseCase saveStockUseCase(StockRepository stockRepository){
        return new SaveStockUseCase(stockRepository);
    }

    @Bean
    public GetAllStockUseCase getAllStockUseCase (StockRepository stockRepository){
        return new GetAllStockUseCase(stockRepository);
    }

    @Bean
    public GetStockUseCase getStockUseCase (StockRepository stockRepository){
       return new  GetStockUseCase (stockRepository);
    }
}
