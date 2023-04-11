package com.acelera.ti.stock.domain.model.exceptions;

public class NotExistStockPageException extends RuntimeException{
    public NotExistStockPageException(){super("No existe la página solicitada");}
}
