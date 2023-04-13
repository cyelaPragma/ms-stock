package com.acelera.ti.stock.domain.model.exceptions.conflict.value;

import com.acelera.ti.stock.domain.model.exceptions.conflict.ApiConflictException;

public class SellPriceException extends ApiConflictException {

    private static final long serialVersionUID = 1L;

    public SellPriceException() {
        super("El precio debe venta debe ser mayor a cero (0) ");
    }
}
