package com.acelera.ti.stock.domain.usecase;

import com.acelera.ti.stock.domain.model.exceptions.ShoppingCartNotFoundException;
import com.acelera.ti.stock.domain.model.gateways.repositories.ShoppingCartRepository;
import com.acelera.ti.stock.domain.model.model.cart.ShoppingCart;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetShoppingCartUseCase {
    private final ShoppingCartRepository shoppingCartRepository;

    public ShoppingCart action(Long userId) {
        ShoppingCart shoppingCart = shoppingCartRepository.getShoppingCartByUserId(userId);
        if (shoppingCart == null) {
            throw new ShoppingCartNotFoundException();
        }
        return shoppingCart;
    }
}
