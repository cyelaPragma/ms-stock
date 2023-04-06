package com.acelera.ti.stock.domain.usecase.orchestrator;

import com.acelera.ti.stock.domain.model.exceptions.ProductNotFoundException;
import com.acelera.ti.stock.domain.model.model.cart.ShoppingCart;
import com.acelera.ti.stock.domain.usecase.GetShoppingCartUseCase;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@RequiredArgsConstructor
public class DeleteProductsByCartUseCase {
    private final GetShoppingCartUseCase getShoppingCartUseCase;

    public void action(Long productToRemoveId, Long userId) {
        ShoppingCart cart = getShoppingCartUseCase.action(userId);
        if (cart.getProducts() == null) {
            throw new ProductNotFoundException();
        }
        cart.getProducts().removeIf(product -> product.getId().equals(productToRemoveId));
        cart.setLastUpdate(LocalDate.now());
    }
}



