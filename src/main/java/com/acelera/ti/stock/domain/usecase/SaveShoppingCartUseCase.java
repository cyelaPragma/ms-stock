package com.acelera.ti.stock.domain.usecase;

import com.acelera.ti.stock.domain.model.gateways.repositories.ShoppingCartRepository;
import com.acelera.ti.stock.domain.model.model.cart.ShoppingCart;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SaveShoppingCartUseCase {
    private final ShoppingCartRepository shoppingCartRepository;

    public ShoppingCart action(ShoppingCart shoppingCart) {
        return shoppingCartRepository.saveShoppingCart(shoppingCart);
    }
}
