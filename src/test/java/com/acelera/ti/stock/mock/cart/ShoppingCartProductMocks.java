package com.acelera.ti.stock.mock.cart;

import com.acelera.ti.stock.domain.model.model.cart.ShoppingCartProduct;
import com.acelera.ti.stock.mock.stock.StockMocks;

import java.util.HashSet;
import java.util.Set;

public class ShoppingCartProductMocks {

    public static ShoppingCartProduct getShoppingCartProduct(Long id) {
        return ShoppingCartProduct.builder()
                .id(id)
                .stock(StockMocks.getStock(id))
                .amount(Math.toIntExact(id))
                .build();
    }

    public static Set<ShoppingCartProduct> getProducts(int size) {
        Set<ShoppingCartProduct> products = new HashSet<>();
        for (int i = 1; i <= size; i++) {
            products.add(ShoppingCartProductMocks.getShoppingCartProduct((long) i));
        }
        return products;
    }

}
