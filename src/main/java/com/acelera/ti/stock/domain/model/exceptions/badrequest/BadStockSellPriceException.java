package com.acelera.ti.stock.domain.model.exceptions.badrequest;

public class BadStockSellPriceException extends RuntimeException{
    public BadStockSellPriceException(){
        super("El precio de venta debe ser mayor a cero (0)");
    }
}