package com.acelera.ti.stock.domain.model.exceptions.notfound;

public class ApiNotFoundException  extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ApiNotFoundException(String message) {
        super(message);
    }

}