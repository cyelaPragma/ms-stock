package com.acelera.ti.stock.mock.cart;

import com.acelera.ti.stock.domain.model.model.cart.ShoppingCart;
import com.acelera.ti.stock.mock.user.PersonMocks;

import java.time.LocalDate;

public class ShoppingCartMocks {
    public static ShoppingCart getShoppingCart(Long id) {
        return ShoppingCart.builder()
                .id(id)
                .user(PersonMocks.getPerson(id))
                .products(ShoppingCartProductMocks.getProducts(5))
                .lastUpdate(LocalDate.now())
                .build();
    }

}

