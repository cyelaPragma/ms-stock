package com.acelera.ti.stock.domain.usecase.orchestrator;

import com.acelera.ti.stock.domain.model.model.cart.ShoppingCart;
import com.acelera.ti.stock.domain.usecase.GetShoppingCartUseCase;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@RequiredArgsConstructor
public class DeleteProductsByCartUseCase {
    private final GetShoppingCartUseCase getShoppingCartUseCase;

    public void action(Long productId, Long userId) {
        ShoppingCart cart = getShoppingCartUseCase.action(userId);
        cart.getProducts().removeIf(product -> product.getId().equals(productId));
        cart.setLastUpdate(LocalDate.now());
    }
}


