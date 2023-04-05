package com.acelera.ti.stock.mock.cart;

import com.acelera.ti.stock.domain.model.model.cart.ShoppingCart;

public class ShoppingCartProductMocks {
    public static ShoppingCart getShoppingCart(){
        return ShoppingCart.builder().build();
    }
}
