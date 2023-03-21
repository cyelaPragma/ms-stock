package com.acelera.ti.stock.domain.model.exceptions;

public class StockEmptyException extends RuntimeException {
    public StockEmptyException() {
        super("No hay articulos en el stock");
    }
}
