package com.acelera.ti.stock.domain.model.exceptions.badrequest;

public class BadParamsException extends RuntimeException{
    public BadParamsException(){
        super("Los parámetros enviados son incorrectos");
    }
}