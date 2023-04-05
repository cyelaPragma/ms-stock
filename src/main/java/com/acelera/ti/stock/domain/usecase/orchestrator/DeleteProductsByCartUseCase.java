package com.acelera.ti.stock.domain.usecase.orchestrator;

import com.acelera.ti.stock.domain.model.model.cart.ShoppingCart;
import com.acelera.ti.stock.domain.usecase.GetShoppingCart;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@RequiredArgsConstructor
public class DeleteProductsByCartUseCase {
    private final GetShoppingCart getShoppingCart;

    public void action(Long productId, Long shoppingCartId) {
        ShoppingCart cart = getShoppingCart.action(shoppingCartId);
        cart.getProducts().removeIf(product -> product.getId().equals(productId));
        cart.setLastUpdate(LocalDate.now());
    }
}

