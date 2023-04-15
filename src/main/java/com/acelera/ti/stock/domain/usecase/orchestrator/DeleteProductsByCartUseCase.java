package com.acelera.ti.stock.domain.usecase.orchestrator;

import com.acelera.ti.stock.domain.model.gateways.repositories.ShoppingCartRepository;
import com.acelera.ti.stock.domain.model.model.cart.ShoppingCart;
import com.acelera.ti.stock.domain.usecase.GetShoppingCartUseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteProductsByCartUseCase {
    private final GetShoppingCartUseCase getShoppingCartUseCase;
    private final ShoppingCartRepository shoppingCartRepository;

    public void action(Long productToRemoveId, Long userId) {
        ShoppingCart cart = getShoppingCartUseCase.action(userId);
        System.out.println("cart::: " +  cart.getProducts().size());
        if (cart.getProducts() != null) {
            shoppingCartRepository.removeProductFromCart(productToRemoveId, cart.getId());
        }
     //   cart.getProducts().removeIf(product -> product.getId().equals(productToRemoveId));
    }
}

