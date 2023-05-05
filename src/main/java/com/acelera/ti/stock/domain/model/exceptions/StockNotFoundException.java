package com.acelera.ti.stock.domain.model.exceptions;

import com.acelera.ti.stock.domain.model.exceptions.notfound.ApiNotFoundException;

public class StockNotFoundException extends ApiNotFoundException {
    public StockNotFoundException() {
        super("No existe el Stock consultado");
    }
}
