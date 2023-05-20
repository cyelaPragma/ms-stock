package com.acelera.ti.stock.domain.model.exceptions;

public class NotExistShoppingCartProductPageException extends RuntimeException{

    public NotExistShoppingCartProductPageException(){
        super("No existe la página solicitada");
    }
}
