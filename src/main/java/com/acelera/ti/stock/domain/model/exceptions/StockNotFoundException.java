package com.acelera.ti.stock.domain.model.exceptions;

public class StockNotFoundException extends RuntimeException{
    public StockNotFoundException() {
        super("No existe el Stock consultado");
    }
}
