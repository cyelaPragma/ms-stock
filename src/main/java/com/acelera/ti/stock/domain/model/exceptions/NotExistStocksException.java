package com.acelera.ti.stock.domain.model.exceptions;

public class NotExistStocksException extends RuntimeException {
    public NotExistStocksException() {
        super("No existen productos registrados");
    }
}
