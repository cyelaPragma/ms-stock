package com.acelera.ti.stock.domain.model.exceptions;

public class TechnicalException extends RuntimeException {
    public TechnicalException(Throwable cause) {
        super(cause);
    }
}
