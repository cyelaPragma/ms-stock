package com.acelera.ti.stock.domain.model.exceptions.conflict;

public class ApiConflictException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ApiConflictException(String message) {
        super(message);
    }
}
