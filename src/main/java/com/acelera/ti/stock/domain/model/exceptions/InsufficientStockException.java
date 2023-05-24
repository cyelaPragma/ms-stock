package com.acelera.ti.stock.domain.model.exceptions;

public class InsufficientStockException extends RuntimeException {
    private final Long productId;
    private final int availableStock;

    public InsufficientStockException(Long productId, int availableStock) {
        super("Insufficient stock for product with ID: " + productId + ". Available stock: " + availableStock);
        this.productId = productId;
        this.availableStock = availableStock;
    }

    public Long getProductId() {
        return productId;
    }

    public int getAvailableStock() {
        return availableStock;
    }
}
