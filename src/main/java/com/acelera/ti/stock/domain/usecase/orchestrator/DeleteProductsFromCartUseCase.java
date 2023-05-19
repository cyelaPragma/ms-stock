package com.acelera.ti.stock.domain.usecase.orchestrator;

import com.acelera.ti.stock.domain.model.gateways.repositories.ShoppingCartRepository;
import com.acelera.ti.stock.domain.model.model.cart.ShoppingCart;
import com.acelera.ti.stock.domain.usecase.GetShoppingCartUseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteProductsFromCartUseCase {
    private final GetShoppingCartUseCase getShoppingCartUseCase;
    private final ShoppingCartRepository shoppingCartRepository;

    public void action(Long productToRemoveId, Long userId) {
        ShoppingCart cart = getShoppingCartUseCase.action(userId);
        shoppingCartRepository.removeProductFromCart(productToRemoveId, cart.getId());
    }
}

