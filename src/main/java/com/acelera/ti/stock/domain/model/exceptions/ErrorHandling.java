package com.acelera.ti.stock.domain.model.exceptions;
public class ErrorHandling extends RuntimeException{
    public ErrorHandling(String message, Throwable cause){
        super(message, cause);
    }
}
