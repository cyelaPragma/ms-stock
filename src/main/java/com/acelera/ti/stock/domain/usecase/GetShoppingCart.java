package com.acelera.ti.stock.domain.usecase;

import com.acelera.ti.stock.domain.model.exceptions.ProductNotFoundException;
import com.acelera.ti.stock.domain.model.exceptions.ShoppingCartNotFoundException;
import com.acelera.ti.stock.domain.model.gateways.repositories.ShoppingCartRepository;
import com.acelera.ti.stock.domain.model.model.cart.ShoppingCart;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetShoppingCart {
    private final ShoppingCartRepository shoppingCartRepository;

    public ShoppingCart action(Long id) {
        ShoppingCart shoppingCart = shoppingCartRepository.getShoppingCartById(id);
        if (shoppingCart == null) {
            throw new ShoppingCartNotFoundException();
        }
        if (shoppingCart.getProducts().isEmpty()) {
            throw new ProductNotFoundException();
        }
        return shoppingCart;
    }
}
