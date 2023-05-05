package com.acelera.ti.stock.domain.model.exceptions.conflict.required;

import com.acelera.ti.stock.domain.model.exceptions.conflict.ApiConflictException;

public class SellPriceRequiredException extends ApiConflictException {

    private static final long serialVersionUID = 1L;

    public SellPriceRequiredException() {
        super("El precio de venta es requerido");
    }
}
