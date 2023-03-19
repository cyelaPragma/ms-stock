package com.acelera.ti.stock.domain.model.exceptions;

public class NotExistProductsException extends RuntimeException {
    public NotExistProductsException() {
        super("No existen productos registrados");
    }
}
