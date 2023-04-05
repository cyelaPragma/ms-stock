package com.acelera.ti.stock.domain.model.gateways.repositories;

import com.acelera.ti.stock.domain.model.model.cart.ShoppingCart;

public interface ShoppingCartRepository {
    ShoppingCart getShoppingCartByUserId(Long userId);
}
