package com.acelera.ti.stock.domain.model.exceptions;

public class ShoppingCartNotFoundException extends RuntimeException{
    public ShoppingCartNotFoundException() {
        super("No existe el carrito de compras consultado");
    }

}
