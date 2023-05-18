package com.acelera.ti.stock.application.configuration;

import com.acelera.ti.stock.domain.model.gateways.repositories.ShoppingCartRepository;
import com.acelera.ti.stock.domain.usecase.GetShoppingCartUseCase;
import com.acelera.ti.stock.domain.usecase.SaveShoppingCartUseCase;
import com.acelera.ti.stock.domain.usecase.orchestrator.DeleteProductsFromCartUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShoppingCartConfig {
    @Bean
    public GetShoppingCartUseCase getShoppingCartUseCase(ShoppingCartRepository shoppingCartRepository) {
        return new GetShoppingCartUseCase(shoppingCartRepository);
    }

    @Bean
    public DeleteProductsFromCartUseCase deleteProductsByCartUseCase(
            GetShoppingCartUseCase getShoppingCartUseCase, ShoppingCartRepository shoppingCartRepository) {
        return new DeleteProductsFromCartUseCase(getShoppingCartUseCase, shoppingCartRepository);
    }

    @Bean
    public SaveShoppingCartUseCase saveShoppingCartUseCase(ShoppingCartRepository shoppingCartRepository) {
        return new SaveShoppingCartUseCase(shoppingCartRepository);
    }
}

