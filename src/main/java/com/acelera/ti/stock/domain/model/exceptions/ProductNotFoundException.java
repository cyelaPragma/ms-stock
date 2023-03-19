package com.acelera.ti.stock.domain.model.exceptions;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException() {
        super("No existe el producto consultado");
    }
}
